package com.kpdigital.mywallpaper;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Arrays;

public class setWallpaper extends AppCompatActivity {


    Intent intent;
    ImageView imageView;
    Button lock, home, both,download;
    RelativeLayout relativeLayout;
    private static final int CHOOSE_IMAGE = 22;
    String[] options = new String[]{
            "Home Screen",
            "Lock Screen",
            "Both"
    };
    AlertDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        relativeLayout = new RelativeLayout(this);

//        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        both = findViewById(R.id.set);
        lock = findViewById(R.id.LockW);
        home = findViewById(R.id.HomeW);
        imageView = findViewById(R.id.finalImage);



        download = findViewById(R.id.download);
        intent = getIntent();
        String url = intent.getStringExtra("image");
//        String tempUrl = url.split("\\?")[0];
//        url = temp[0] + "?q=75&fm=jpg";
//        url = url.replace("images", "hd");
//        Toast.makeText(this, url, Toast.LENGTH_SHORT).show();
//        Log.e("URL",url);
        Glide.with(getApplicationContext()).load(url).into(imageView);

        both.setOnClickListener(new View.OnClickListener() {

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    try {
                        Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                        wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK | WallpaperManager.FLAG_SYSTEM);
                        Toast.makeText(setWallpaper.this, "Wallpaper set successfully!", Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        Log.e("TAG", "onResourceReady: " + e.getMessage());
                    }
                }





        });

        lock.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_LOCK);
                    Toast.makeText(setWallpaper.this, "Wallpaper set successfully!", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Log.e("TAG", "onResourceReady: " + e.getMessage());
                }
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap, null, false, WallpaperManager.FLAG_SYSTEM);
                    Toast.makeText(setWallpaper.this, "Wallpaper set successfully!", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Log.e("TAG", "onResourceReady: " + e.getMessage());
                }
            }
        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                Uri uri = Uri.parse(url);
                DownloadManager.Request request = new DownloadManager.Request(uri);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                downloadManager.enqueue(request);
            }
        });
    }

}