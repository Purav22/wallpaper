package com.kpdigital.mywallpaper;

import static com.kpdigital.mywallpaper.MainActivity.height;
import static com.kpdigital.mywallpaper.MainActivity.width;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class categoryResult extends AppCompatActivity {
    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    //    EditText editText;
//    ImageButton search;
    Adapter adapter;
    GridLayoutManager manager;
    private int page = 1;
    private int pageSize = 30;
    private boolean isLoading;
    private boolean isLastPage;
    String query;
    Intent intent;
    TextView textView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_result);
        recyclerView = findViewById(R.id.recyclerView7);
        progressBar = (ProgressBar)findViewById(R.id.progressbar1);
        Sprite doubleBounce = new FadingCircle();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.VISIBLE);
        textView = findViewById(R.id.fail_msg0);
//        editText = getActivity().findViewById(R.id.searchEditText);
        manager = new GridLayoutManager(this ,2);
        TextView textView = findViewById(R.id.nameSearch);
        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(manager);
        adapter = new Adapter(this, modelClassList);
        recyclerView.setAdapter(adapter);
        intent = getIntent();
        query = intent.getStringExtra("image");
        textView.setText(query);

        getSearchImage(query);
//        getActivity().findViewById(R.id.searchIcon).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                String query = editText.getText().toString().trim().toLowerCase();
//                Log.i("printed", query);
//                Toast.makeText(view.getContext(), query, Toast.LENGTH_LONG).show();
//                if(query.isEmpty()) {
////                    Toast.makeText(container.getContext(),"Enter any word", Toast.LENGTH_SHORT).show();
//                }else{
//                    getSearchImage(query);
//                }
//            }
//        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItem = manager.getChildCount();
                int totalItem = manager.getItemCount();

                int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
                if(!isLoading && !isLastPage){
                    if((visibleItem + firstVisibleItemPosition >= totalItem) && firstVisibleItemPosition >= 0 && totalItem >= pageSize){
                        page++;
                        getSearchImage(query);
                    }
                }
            }
        });

    }

    private void getSearchImage(String query) {
        isLoading = true;
        APIUtilities.getAPIInterface().getSearchImage(query,"portrait",100,width, height,page,30).enqueue(new Callback<searchModel>() {
            @Override
            public void onResponse(Call<searchModel> call, Response<searchModel> response) {
//                modelClassList.clear();
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }
                progressBar.setVisibility(View.INVISIBLE);
                isLoading = false;
                if(modelClassList.size() > 0) {
                    isLastPage= modelClassList.size() < pageSize;
                }else{
                    isLastPage = true;
                }
            }

            @Override
            public void onFailure(Call<searchModel> call, Throwable t) {
                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }

        });

    }
}