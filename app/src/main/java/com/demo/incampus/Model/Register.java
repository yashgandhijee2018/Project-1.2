package com.demo.incampus.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register  {

    @SerializedName("accessToken")
    @Expose
    private  String accessToken;

    public Register() {
    }

    public Register(String accessToken) {
        this.accessToken = accessToken;
    }

    //string getter


    public String getAccessToken() {
        return accessToken;
    }

    //setter


    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

}
