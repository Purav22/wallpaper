package com.kpdigital.mywallpaper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageButton;


import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    static int height;
    static int width;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    ImageButton btn;
    Animation topAnimation;
    private DrawerLayout drawer;
    static ArrayList<String> liked_imageLink;
    static ArrayList<String> liked_name;
    static ArrayList<String> liked_urlOfUser;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        liked_imageLink = new ArrayList<>();
        liked_name = new ArrayList<>();
        liked_urlOfUser = new ArrayList<>();

        SharedPreferences sharedPreferences = this.getSharedPreferences("com.kpdigital.mywallpaper", Context.MODE_PRIVATE);

        try {
            liked_name = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("name",ObjectSerializer.serialize(new ArrayList<String>())));
            liked_imageLink = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("image",ObjectSerializer.serialize(new ArrayList<String>())));
            liked_urlOfUser = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("userUrl",ObjectSerializer.serialize(new ArrayList<String>())));
        }catch (Exception e){
            e.printStackTrace();
        }


        drawer = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);


        drawer.addDrawerListener(toggle);
        toggle.syncState();

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewPager);
        btn = findViewById(R.id.searchIconButton);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.home));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.top));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.car));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.leaves));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.food));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.luggage));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.menu));

        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);

        pageAdapter page = new pageAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(page);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        tabLayout.setOnTabSelectedListener(this);

//        getSupportActionBar().hide();
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_liked:
                        Intent intent = new Intent(getApplicationContext(), likedWallpaper.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_aboutUs:

                        break;
                    case R.id.nav_rateUs:
                        try {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + getPackageName())));
                        } catch (android.content.ActivityNotFoundException anfe) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName())));
                        }
                        break;
                    case R.id.nav_shareApp:
                        try {
                            Intent shareIntent = new Intent(Intent.ACTION_SEND);
                            shareIntent.setType("text/plain");
                            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "My application name");
                            String shareMessage = "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n" + "download this Wallpaper application to personalized your device";
                            shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                            startActivity(Intent.createChooser(shareIntent, "choose one"));
                        } catch (Exception e) {

                        }
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);

                return false;
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), searchForImage.class);
                startActivity(intent);
            }
        });


    }



    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}