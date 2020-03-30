package com.demo.incampus.Model;

public class Messages {

    private String profile_name__, message_, message_time_;
    private int profile_photo_;

    public Messages(String profile_name__, String message_, String message_time_, int profile_photo_) {
        this.profile_name__ = profile_name__;
        this.message_ = message_;
        this.message_time_ = message_time_;
        this.profile_photo_ = profile_photo_;
    }

    public String getProfile_name__() {
        return profile_name__;
    }

    public String getMessage_() {
        return message_;
    }

    public String getMessage_time_() {
        return message_time_;
    }

    public int getProfile_photo_() {
        return profile_photo_;
    }
}
