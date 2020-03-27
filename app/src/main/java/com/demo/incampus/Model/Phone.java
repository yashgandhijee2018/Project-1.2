package com.demo.incampus.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Phone {

    @SerializedName("Status")
    @Expose
    private String status;

    @SerializedName("Details")
    @Expose
    private String details;

    public String getStatus() {
        return status;
    }

    public String getDetails() {
        return details;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
