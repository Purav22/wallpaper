package com.kpdigital.mywallpaper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jgabrielfreitas.core.BlurImageView;

public class allcategoryAdapter extends RecyclerView.Adapter<allcategoryAdapter.ViewHolder> {
    Context context;
    String[] name;
    int[] images;

    public allcategoryAdapter(Context context, String[] name, int[] images) {
        this.context = context;
        this.name = name;
        this.images = images;
    }

    @NonNull
    @Override
    public allcategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_category_layout, null, false);
        return new allcategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull allcategoryAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.textView.setText(name[position]);
        holder.imageView1.setImageResource(images[position]);
        holder.imageView1.setBlur(20);
        holder.imageView2.setImageResource(images[position]);


        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, categoryResult.class);
                intent.putExtra("image", name[position]);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
                context.stopService(intent);

            }
        });
        holder.imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, categoryResult.class);
                intent.putExtra("image", name[position]);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, categoryResult.class);
                intent.putExtra("image", name[position]);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;
        BlurImageView imageView1;
        ImageView imageView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.category_text);
            imageView1 = itemView.findViewById(R.id.category_image1);
            imageView2 = itemView.findViewById(R.id.category_image2);
        }
    }
}
