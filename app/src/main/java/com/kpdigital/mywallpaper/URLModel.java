package com.kpdigital.mywallpaper;

public class URLModel {
    int id,width,height;
    private String regular;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getRegular() {
        return regular;
    }

    public void setRegular(String regular) {
        this.regular = regular;
    }

    public URLModel(int id, int width, int height, String regular) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.regular = regular;
    }

    //    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getOriginal() {
//        return original;
//    }
//
//    public void setOriginal(String original) {
//        this.original = original;
//    }
//
//    public String getPortrait() {
//        return portrait;
//    }
//
//    public void setPortrait(String portrait) {
//        this.portrait = portrait;
//    }
//
//    public URLModel(int id, String portrait, String original) {
//        this.id = id;
//        this.portrait = portrait;
//        this.original = original;
//    }
}
