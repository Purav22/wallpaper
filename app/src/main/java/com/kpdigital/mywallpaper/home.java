package com.kpdigital.mywallpaper;

import static com.kpdigital.mywallpaper.MainActivity.width;
import static com.kpdigital.mywallpaper.MainActivity.height;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.FadingCircle;
import com.github.ybq.android.spinkit.style.Wave;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class home extends Fragment {
    private ArrayList<ImageModel> modelClassList;
    private RecyclerView recyclerView;
//    EditText editText;
//    ImageButton search;
    Adapter adapter;
    ProgressBar progressBar;

    private int page = 1;
    private int pageSize = 30;
    private boolean isLoading;
    private boolean isLastPage;
    GridLayoutManager manager;
    TextView textView;
    @Nullable
    @Override
    public View onCreateView(@NonNull  LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.home,container,false);
        recyclerView = view.findViewById(R.id.recyclerView0);
        progressBar = view.findViewById(R.id.progressbar5);
        Sprite doubleBounce = new FadingCircle();
        progressBar.setIndeterminateDrawable(doubleBounce);
        progressBar.setVisibility(View.VISIBLE);
//        editText = getActivity().findViewById(R.id.searchEditText);
        manager = new GridLayoutManager(container.getContext(),2);
//        search = getActivity().findViewById(R.id.searchIcon);
        modelClassList = new ArrayList<>();
        recyclerView.setLayoutManager(manager);
        adapter = new Adapter(container.getContext(),modelClassList);
        recyclerView.setAdapter(adapter);
        textView = view.findViewById(R.id.fail_msg4);
        findPhotos();
//        search.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String query = editText.getText().toString().trim().toLowerCase();
//                Toast.makeText(view.getContext(), query, Toast.LENGTH_LONG).show();
//
//                Log.i("printed", query);
//
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
                        findPhotos();
                    }
                }
            }
        });
        return view;
    }

//    private void getSearchImage(String query) {
//        isLoading = true;
//        APIUtilities.getAPIInterface().getSearchImage(query,1,30).enqueue(new Callback<searchModel>() {
//            @Override
//            public void onResponse(Call<searchModel> call, Response<searchModel> response) {
//
//                if(response.isSuccessful()){
//                    modelClassList.addAll(response.body().getPhotos());
//                    adapter.notifyDataSetChanged();
//                }
//                isLoading = false;
//                if(modelClassList.size() > 0) {
//                    isLastPage= modelClassList.size() < pageSize;
//                }else{
//                    isLastPage = true;
//                }
//            }
//
//            @Override
//            public void onFailure(Call<searchModel> call, Throwable t) {
//
//            }
//        });
//    }
    private void findPhotos() {

        isLoading = true;
        APIUtilities.getAPIInterface().getImage("portrait",100,page,80).enqueue(new Callback<List<ImageModel>>() {
            @Override
            public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {
                //modelClassList.clear();
                if(response.isSuccessful()){
//                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_LONG).show();
                    modelClassList.addAll(response.body());
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
            public void onFailure(Call<List<ImageModel>> call, Throwable t) {
                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });


    }
}
