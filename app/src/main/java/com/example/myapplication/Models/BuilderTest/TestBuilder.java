package com.example.myapplication.Models.BuilderTest;

public class TestBuilder {
    private int id;
    private String title;
    private String desc;
    private Object image;

    public TestBuilder setId(int id) {
        this.id = id;
        return this;
    }

    public TestBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public TestBuilder setDesc(String desc) {
        this.desc = desc;
        return this;
    }

    public TestBuilder setImage(Object image) {
        this.image = image;
        return this;
    }

    public Test createTest() {
        return new Test(id, title, desc, image);
    }
}