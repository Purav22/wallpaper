package com.kpdigital.mywallpaper;

import static com.kpdigital.mywallpaper.MainActivity.height;
import static com.kpdigital.mywallpaper.MainActivity.width;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class searchForImage extends AppCompatActivity {

    EditText editText;
    ImageButton btn;
    private int page = 1;
    private int pageSize = 30;
    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
    private boolean isLoading;
    private boolean isLastPage;

    Adapter adapter;
    String query;
    GridLayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_for_image);

        editText = findViewById(R.id.searchEditText);
        btn = findViewById(R.id.searchIcon);
        recyclerView = findViewById(R.id.recyclerViewsearch);
        manager = new GridLayoutManager(this,2);

        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(manager);
        adapter = new Adapter(this,modelClassList);
        recyclerView.setAdapter(adapter);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modelClassList.clear();
                query = editText.getText().toString().trim().toLowerCase();
//                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
                if(query.length() == 0) {
                    Toast.makeText(getApplicationContext(),"Enter any keyword", Toast.LENGTH_SHORT).show();
                }else{
                    getSearchImage(query);
                }
            }
        });

        editText.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    modelClassList.clear();
                    query = editText.getText().toString().trim().toLowerCase();
//                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();
                    if(query.length() == 0) {
                        Toast.makeText(getApplicationContext(),"Enter any keyword", Toast.LENGTH_SHORT).show();
                    }else{
                        getSearchImage(query);
                    }
                    hideKeyboard(v);
                    return true;
                }
                return false;
            }
        });
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

        Log.e("Done", String.valueOf(height));
    }

    private void getSearchImage(String query) {
        isLoading = true;
        APIUtilities.getAPIInterface().getSearchImage(query,"portrait",100,width,height,page,30).enqueue(new Callback<searchModel>() {
            @Override
            public void onResponse(Call<searchModel> call, Response<searchModel> response) {
//                modelClassList.clear();
                if(response.isSuccessful()){
                    modelClassList.addAll(response.body().getPhotos());
                    adapter.notifyDataSetChanged();
                }isLoading = false;
                if(modelClassList.size() > 0) {
                    isLastPage= modelClassList.size() < pageSize;
                }else{
                    isLastPage = true;
                }
            }

            @Override
            public void onFailure(Call<searchModel> call, Throwable t) {

            }
        });
    }

    public void hideKeyboard(View view) {
        try {
            InputMethodManager imm = (InputMethodManager) getSystemService(getApplicationContext().INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        } catch(Exception ignored) {
        }
    }
}