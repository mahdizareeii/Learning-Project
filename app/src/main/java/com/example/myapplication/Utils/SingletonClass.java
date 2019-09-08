package com.example.myapplication.Utils;

import com.example.myapplication.JetPack.Room.AsyncTaskRoom.RoomAsyncTaskType;
import com.example.myapplication.JetPack.Room.AsyncTaskRoom.RoomAsyncTask;
import com.example.myapplication.JetPack.Room.StudentDao;

public class SingletonClass {

    //AsyncTask not need singleton because the executed only once

    /*private static RoomAsyncTask roomAsyncTask;

    public static RoomAsyncTask getRoomAsyncTaskInstance(StudentDao studentDao, RoomAsyncTaskType asyncType) {
        if (roomAsyncTask == null)
            roomAsyncTask = new RoomAsyncTask(studentDao, asyncType);
        return roomAsyncTask;
    }*/

}
