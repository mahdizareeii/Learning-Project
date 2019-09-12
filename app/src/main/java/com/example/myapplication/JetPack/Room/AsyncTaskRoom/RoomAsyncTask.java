package com.example.myapplication.JetPack.Room.AsyncTaskRoom;

import android.os.AsyncTask;

import com.example.myapplication.JetPack.Room.Student;
import com.example.myapplication.JetPack.Room.StudentDao;

public class RoomAsyncTask extends AsyncTask<Object, Void, Object> {

    private StudentDao studentDao;
    private RoomAsyncTaskType asyncType;

    public RoomAsyncTask(StudentDao studentDao, RoomAsyncTaskType asyncType) {
        this.studentDao = studentDao;
        this.asyncType = asyncType;
    }

    @Override
    protected Object doInBackground(Object... objects) {
        switch (asyncType) {

            case GETObjectById:
                return studentDao.getStudentById((int) objects[0]);

            case GETObjectByStudentId:
                return studentDao.getStudentByStudentId((String) objects[0]);

            case GETList:
                return studentDao.getAllStudent();

            case GETLiveData:
                return studentDao.getAllStudentLiveData();

            case INSERTObject:
                studentDao.insertStudent((Student) objects[0]);

            case UPDATEName:
                studentDao.updateStudent((Student) objects[0]);

            case DELETEStudent:
                studentDao.deleteStudent((Student) objects[0]);
        }
        return null;
    }
}
