package com.example.myapplication.RxJava.RxJava2;

import java.util.ArrayList;
import java.util.List;

public class DataSource {
    public static List<Task> createTaskList() {
        List<Task> list = new ArrayList<>();
        list.add(new Task("desc 1", true, 3));
        list.add(new Task("desc 2", false, 2));
        list.add(new Task("desc 3", true, 1));
        list.add(new Task("desc 4", false, 0));
        list.add(new Task("desc 5", true, 50));
        return list;
    }
}
