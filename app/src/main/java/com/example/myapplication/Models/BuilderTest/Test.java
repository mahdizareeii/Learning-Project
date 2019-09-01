package com.example.myapplication.Models.BuilderTest;

import com.google.gson.annotations.SerializedName;

public class Test {
    public Test(int id, String title, String desc, Object image) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.image = image;
    }

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("desc")
    private String desc;

    @SerializedName("image")
    private Object image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }
}
