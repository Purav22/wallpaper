package com.kpdigital.mywallpaper;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.Wave;
import com.jgabrielfreitas.core.BlurImageView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class allCategory extends Fragment {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    allcategoryAdapter adapter;
    GridLayoutManager manager;
    String[] name = {"3D","Abstract","Aircraft", "God", "Animal", "Anime", "Architecture", "Art", "Babies",
            "Candle", "Cartoon", "Fantasy", "Flowers","Food", "Game", "Love", "Mask", "Material",
            "Minimal", "Mountain", "Multi Color", "Music", "Nature", "Neon", "Fireworks", "Ocean",
            "Quote" ,"Reflections", "Space", "Sports", "Tourism", "Vehicles",
            "Water", "Laptop", "Code","Video", "Photography", "Christmas"};

    int[] image = {R.drawable.threed, R.drawable.abstractimage, R.drawable.aircraft, R.drawable.god
                    ,R.drawable.animal, R.drawable.anime, R.drawable.architecture,R.drawable.art,R.drawable.babies
                    ,R.drawable.candle,R.drawable.cartoon,R.drawable.fantasy,R.drawable.flowers
                    ,R.drawable.foodimage,R.drawable.game,R.drawable.love,R.drawable.mask
                    ,R.drawable.material,R.drawable.minimal,R.drawable.mountain,R.drawable.multicolor
                    ,R.drawable.music,R.drawable.nature,R.drawable.neon,R.drawable.firework
                    ,R.drawable.ocean,R.drawable.quote,R.drawable.reflection,R.drawable.space
                    ,R.drawable.sports,R.drawable.tourism,R.drawable.carimage,R.drawable.water
                    ,R.drawable.laptop,R.drawable.code,R.drawable.video,R.drawable.photography
                    ,R.drawable.christmas};
    @Nullable
    @Override
     public View onCreateView(@NonNull  LayoutInflater inflater,  ViewGroup container,  Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.all_category,container,false);
        recyclerView = view.findViewById(R.id.recyclerView6);
        manager = new GridLayoutManager(container.getContext(),1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        adapter = new allcategoryAdapter(container.getContext(), name, image);
        recyclerView.setAdapter(adapter);




        return view;
    }

}
