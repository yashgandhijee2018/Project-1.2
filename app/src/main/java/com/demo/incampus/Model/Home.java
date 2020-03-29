package com.demo.incampus.Model;

public class Home {

    private String topic, name, time, content, messages, hearts;
    private int profilePhoto;

    public Home(String topic, String name, String time, String content, String messages, String hearts, int profilePhoto) {
        this.topic = topic;
        this.name = name;
        this.time = time;
        this.content = content;
        this.messages = messages;
        this.hearts = hearts;
        this.profilePhoto = profilePhoto;
    }

    public String getTopic() {
        return topic;
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public String getContent() {
        return content;
    }

    public String getMessages() {
        return messages;
    }

    public String getHearts() {
        return hearts;
    }

    public int getProfilePhoto() {
        return profilePhoto;
    }
}
