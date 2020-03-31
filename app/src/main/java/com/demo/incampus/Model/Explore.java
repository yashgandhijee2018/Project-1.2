package com.demo.incampus.Model;

public class Explore {

    private int profile_photo;
    private String name;

    public Explore(int profile_photo, String name) {
        this.profile_photo = profile_photo;
        this.name = name;
    }

    public int getProfile_photo() {
        return profile_photo;
    }

    public String getName() {
        return name;
    }
}
