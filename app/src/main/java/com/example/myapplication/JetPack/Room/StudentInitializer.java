package com.example.myapplication.JetPack.Room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentInitializer extends AndroidViewModel {
    //we can't use ViewModel because ViewModel can not have constructor and context
    //so we use AndroidViewModel in this class


    private static final String TAG = StudentInitializer.class.getName();
    private StudentDao studentDao;

    public StudentInitializer(Application application) {
        super(application);
        studentDao = StudentDataBase.getInstance(application.getApplicationContext()).getStudentDao();
    }

    public LiveData<List<Student>> getStudentListLiveData() {
        //return LiveData<List<Student>>
        return studentDao.getAllStudentLiveData();
    }

    public void insertStudent(Student student) {
        new InsertAsyncTask(studentDao).execute(student);
    }

    public class InsertAsyncTask extends AsyncTask<Student, Void, Void> {

        private StudentDao studentDao;

        private InsertAsyncTask(StudentDao studentDao) {
            this.studentDao = studentDao;
        }

        @Override
        protected Void doInBackground(Student... students) {
            studentDao.insertStudent(students[0]);
            return null;
        }
    }

}
