package com.kpdigital.mywallpaper;



import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.HashSet;

public class likedAdapter extends RecyclerView.Adapter<likedAdapter.ViewHolder> {
    Context context;

    public likedAdapter(Context applicationContext) {
        this.context = applicationContext;
    }

    @NonNull
    @Override
    public likedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null, false);
        return new likedAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull likedAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Glide.with(context).load(MainActivity.liked_imageLink.get(position)).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, setWallpaper.class);
                intent.putExtra("image", MainActivity.liked_imageLink.get(position));
                intent.putExtra("name", MainActivity.liked_name.get(position));
                intent.putExtra("userUrl", MainActivity.liked_urlOfUser.get(position));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return MainActivity.liked_imageLink.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
        }
    }
}
