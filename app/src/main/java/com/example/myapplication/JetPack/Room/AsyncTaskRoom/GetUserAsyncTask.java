package com.example.myapplication.JetPack.Room.AsyncTaskRoom;

import android.os.AsyncTask;

import com.example.myapplication.JetPack.Room.Student;
import com.example.myapplication.JetPack.Room.StudentDao;

public class GetUserAsyncTask extends AsyncTask<Integer, Void, Student> {

    //get User By Id and return the user object
    private StudentDao studentDao;

    public GetUserAsyncTask(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    protected Student doInBackground(Integer... integers) {
        return studentDao.getUserById(integers[0]);
    }
}
