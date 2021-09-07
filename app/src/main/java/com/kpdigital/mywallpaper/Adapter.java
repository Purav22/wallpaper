package com.kpdigital.mywallpaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.method.LinkMovementMethod;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    ArrayList<ImageModel> wallpaper;

    public Adapter(Context context, ArrayList<ImageModel> wallpaper) {
        this.context = context;
        this.wallpaper = wallpaper;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {


//        holder.textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.textView.setMovementMethod(LinkMovementMethod.getInstance());
//                holder.textView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent browser = new Intent(Intent.ACTION_VIEW);
//                        browser.setData(Uri.parse("https://www.pexels.com/"));
//                        browser.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        context.startActivity(browser);
//                    }
//                });
//            }
//        });


        Glide.with(context).load(wallpaper.get(position).getSrc().getRegular()).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, setWallpaper.class);
                intent.putExtra("image", wallpaper.get(position).getSrc().getRegular());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return wallpaper.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;






        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
        }
    }
}
