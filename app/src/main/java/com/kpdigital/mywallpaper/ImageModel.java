package com.kpdigital.mywallpaper;

public class ImageModel {

    private URLModel urls;

    public URLModel getSrc() {
        return urls;
    }

    public void setSrc(URLModel src) {
        this.urls = src;
    }

    public ImageModel(URLModel src) {
        this.urls = src;
    }
}
