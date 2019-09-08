package com.example.myapplication.JetPack.Room.AsyncTaskRoom;

import android.os.AsyncTask;

import com.example.myapplication.JetPack.Room.Student;
import com.example.myapplication.JetPack.Room.StudentDao;

public class InsertUserAsyncTask extends AsyncTask<Student, Void, Void> {

        private StudentDao studentDao;

        public InsertUserAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students[0]);
            return null;
        }
    }