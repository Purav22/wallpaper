package com.kpdigital.mywallpaper;

import java.util.ArrayList;

public class searchModel {

    private ArrayList<ImageModel> results;

    public ArrayList<ImageModel> getPhotos() {
        return results;
    }

    public void setPhotos(ArrayList<ImageModel> results) {
        this.results = results;
    }

    public searchModel(ArrayList<ImageModel> results) {
        this.results = results;
    }
}
