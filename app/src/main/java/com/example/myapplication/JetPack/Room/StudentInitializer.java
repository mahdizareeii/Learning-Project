package com.example.myapplication.JetPack.Room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.JetPack.Room.AsyncTaskRoom.GetUserAsyncTask;
import com.example.myapplication.JetPack.Room.AsyncTaskRoom.InsertUserAsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

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

    public Student getStudentById(int id) {
        GetUserAsyncTask getUserAsyncTask = new GetUserAsyncTask(studentDao);
        getUserAsyncTask.execute(id);
        try {
            return getUserAsyncTask.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student getUserByStudentId(String id) {
        return studentDao.getUserByStudentId(id);
    }

    public void insertStudent(Student student) {
        new InsertUserAsyncTask(studentDao).execute(student);
    }

}
