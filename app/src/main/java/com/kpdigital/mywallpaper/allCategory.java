package com.kpdigital.mywallpaper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class allCategory extends Fragment {
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
    @Nullable
    @Override
     public View onCreateView(@NonNull  LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.all_category,container,false);
    }
}
