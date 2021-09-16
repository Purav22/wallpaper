package com.kpdigital.mywallpaper;

public class ImageModel {

    private URLModel urls;
    private userModel user;

    public ImageModel(URLModel urls, userModel user) {
        this.urls = urls;
        this.user = user;
    }


    public userModel getUser() {
        return user;
    }

    public void setUser(userModel user) {
        this.user = user;
    }

    public URLModel getSrc() {
        return urls;
    }

    public void setSrc(URLModel src) {
        this.urls = src;
    }

}
