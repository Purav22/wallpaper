package com.kpdigital.mywallpaper;



import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DownloadManager;
import android.app.WallpaperManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;


public class setWallpaper extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Intent intent;
    ImageView imageView;
    Animation rotate_open, rotate_close, from_bottom, to_bottom;
    FloatingActionButton heart,download,plus,homeScreen, lockScreen, bothScreen;
    TextView homeText,lockText, bothText, add;
    RelativeLayout relativeLayout;
    FloatingActionButton info;
    String url;
    private static final int CHOOSE_IMAGE = 22;
    String[] options = new String[]{
            "Home Screen",
            "Lock Screen",
            "Both"
    };
    AlertDialog dialog;
    Dialog d;
    private int REQUSTCODE = 100;
    private boolean clicked = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        relativeLayout = new RelativeLayout(this);

        d = new Dialog(setWallpaper.this);
        d.setContentView(R.layout.info_photo_dialog);
        d.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        d.setCancelable(true);
        d.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;


        rotate_close = AnimationUtils.loadAnimation(this,R.anim.button_rotate_close_anim);
        rotate_open = AnimationUtils.loadAnimation(this,R.anim.button_rotate_open_anim);
        from_bottom = AnimationUtils.loadAnimation(this,R.anim.button_from_bottom_anim);
        to_bottom = AnimationUtils.loadAnimation(this,R.anim.button_to_bottom_anim);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
        final WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        imageView = findViewById(R.id.finalImage);
        heart = findViewById(R.id.heartIcon);
        download = findViewById(R.id.downloadIcon);
        plus = findViewById(R.id.plusIcon);
        homeScreen = findViewById(R.id.homeScreenIcon);
        bothScreen = findViewById(R.id.bothScreenIcon);
        lockScreen = findViewById(R.id.lockScreenIcon);
        homeText = findViewById(R.id.homeScreenText);
        lockText = findViewById(R.id.lockScreenText);
        bothText = findViewById(R.id.bothScreenText);
        add = findViewById(R.id.add);
        info = findViewById(R.id.infoButton);
        intent = getIntent();
        url = intent.getStringExtra("image");
        String name = intent.getStringExtra("name");
        String userUrl = intent.getStringExtra("userUrl");

        TextView userName = d.findViewById(R.id.userName);
        userName.setText(name);
        TextView urlForUser = d.findViewById(R.id.userUrl);
        urlForUser.setText(userUrl);

        Glide.with(getApplicationContext()).load(url).into(imageView);
        sharedPreferences = this.getSharedPreferences("com.kpdigital.mywallpaper", Context.MODE_PRIVATE);

        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                d.show();
            }
        });

        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = false;

                for(int i = 0; i < MainActivity.liked_imageLink.size(); i++){

                    if(MainActivity.liked_imageLink.get(i).equals(url)){
                        check = true;
                        MainActivity.liked_name.remove(i);
                        MainActivity.liked_imageLink.remove(i);
                        MainActivity.liked_urlOfUser.remove(i);
                        Toast.makeText(getApplicationContext(), "Removed", Toast.LENGTH_SHORT).show();
                        break;
                    }
                }
                if(!check){

                    MainActivity.liked_name.add(name);
                    MainActivity.liked_imageLink.add(url);
                    MainActivity.liked_urlOfUser.add(userUrl);
                    Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();

                }

                try {
                    sharedPreferences.edit().putString("name",ObjectSerializer.serialize(MainActivity.liked_name)).apply();
                    sharedPreferences.edit().putString("image",ObjectSerializer.serialize(MainActivity.liked_imageLink)).apply();
                    sharedPreferences.edit().putString("userUrl",ObjectSerializer.serialize(MainActivity.liked_urlOfUser)).apply();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(likedWallpaper.liked_adapter != null)
                    likedWallpaper.liked_adapter.notifyDataSetChanged();





            }


        });

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogForPermission();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onPlushButtonClick();
            }
        });


        homeScreen.setOnClickListener(new View.OnClickListener() {
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

        lockScreen.setOnClickListener(new View.OnClickListener() {
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

        bothScreen.setOnClickListener(new View.OnClickListener() {
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


    }

    private void showDialogForPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUSTCODE);
            }
            else{
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUSTCODE);
            }
        }
        else if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){
            download();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUSTCODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                download();
            } else {
                showDialogForPermission();
            }
        }
    }

    private void onPlushButtonClick() {
        setVisibility(clicked);
        setAnimation(clicked);
        setClicking(clicked);
        if(!clicked) clicked = true;
        else clicked = false;
    }

    private void setAnimation(boolean click) {
        if(!click){
            homeScreen.startAnimation(from_bottom);
            lockScreen.startAnimation(from_bottom);
            bothScreen.startAnimation(from_bottom);
            homeText.startAnimation(from_bottom);
            lockText.startAnimation(from_bottom);
            bothText.startAnimation(from_bottom);
            plus.startAnimation(rotate_open);
        }else{
            homeScreen.startAnimation(to_bottom);
            lockScreen.startAnimation(to_bottom);
            bothScreen.startAnimation(to_bottom);
            homeText.startAnimation(to_bottom);
            lockText.startAnimation(to_bottom);
            bothText.startAnimation(to_bottom);
            plus.startAnimation(rotate_close);
        }
    }



    private  void setVisibility(boolean click){
        if(!click){
            homeScreen.setVisibility(View.VISIBLE);
            lockScreen.setVisibility(View.VISIBLE);
            bothScreen.setVisibility(View.VISIBLE);
            homeText.setVisibility(View.VISIBLE);
            lockText.setVisibility(View.VISIBLE);
            bothText.setVisibility(View.VISIBLE);
        }else {
            homeScreen.setVisibility(View.INVISIBLE);
            lockScreen.setVisibility(View.INVISIBLE);
            bothScreen.setVisibility(View.INVISIBLE);
            homeText.setVisibility(View.INVISIBLE);
            lockText.setVisibility(View.INVISIBLE);
            bothText.setVisibility(View.INVISIBLE);
        }
    }

    private void setClicking(boolean click) {
        if(!click){
            homeScreen.setClickable(true);
            lockScreen.setClickable(true);
            bothScreen.setClickable(true);
            homeText.setClickable(true);
            lockText.setClickable(true);
            bothText.setClickable(true);
        }else {
            homeScreen.setClickable(false);
            lockScreen.setClickable(false);
            bothScreen.setClickable(false);
            homeText.setClickable(false);
            lockText.setClickable(false);
            bothText.setClickable(false);
        }
    }


    public void download(){
        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(url);
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        downloadManager.enqueue(request);
        Toast.makeText(getApplicationContext(), "Successfully Downloaded", Toast.LENGTH_SHORT).show();
    }

}