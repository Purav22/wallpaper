package com.kpdigital.mywallpaper;

import java.util.ArrayList;

public class userModel {

    private String name;
    private userDetails links;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public userDetails getLinks() {
        return links;
    }

    public void setLinks(userDetails links) {
        this.links = links;
    }

    public userModel(String name, userDetails links) {
        this.name = name;
        this.links = links;
    }
}
