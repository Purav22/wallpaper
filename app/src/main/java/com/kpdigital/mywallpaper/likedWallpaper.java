package com.kpdigital.mywallpaper;



import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class likedWallpaper extends AppCompatActivity {
    RecyclerView recyclerView;
    GridLayoutManager manager;
    static likedAdapter liked_adapter;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liked_wallpapers);


        recyclerView = findViewById(R.id.recyclerView8);
        manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);

        liked_adapter = new likedAdapter(this);
        recyclerView.setAdapter(liked_adapter);



    }

}
