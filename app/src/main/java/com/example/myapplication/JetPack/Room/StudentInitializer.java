package com.example.myapplication.JetPack.Room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.myapplication.JetPack.Room.AsyncTaskRoom.RoomAsyncTask;
import com.example.myapplication.JetPack.Room.AsyncTaskRoom.RoomAsyncTaskType;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class StudentInitializer extends AndroidViewModel {
    //we can't use ViewModel because ViewModel can not have constructor and context
    //so we use AndroidViewModel in this class


    private static final String TAG = StudentInitializer.class.getName();
    private StudentDao studentDao;
    private RoomAsyncTask roomAsyncTask;

    public StudentInitializer(Application application) {
        super(application);
        studentDao = StudentDataBase.getInstance(application.getApplicationContext()).getStudentDao();
    }

    //get

    public LiveData<List<Student>> getStudentListLiveData() {
        return studentDao.getAllStudentLiveData();
    }

    public List<Student> getStudentList() throws ExecutionException, InterruptedException {
        RoomAsyncTask getAllUserAsyncTask = new RoomAsyncTask(studentDao, RoomAsyncTaskType.GETList);
        getAllUserAsyncTask.execute();
        return (List<Student>) getAllUserAsyncTask.get();
    }

    public Student getStudentById(int id) throws ExecutionException, InterruptedException {
        roomAsyncTask = new RoomAsyncTask(studentDao, RoomAsyncTaskType.GETObjectById);
        roomAsyncTask.execute(id);
        return (Student) roomAsyncTask.get();
    }

    public Student getStudentByStudentId(String id) throws ExecutionException, InterruptedException {
        roomAsyncTask = new RoomAsyncTask(studentDao, RoomAsyncTaskType.GETObjectByStudentId);
        roomAsyncTask.execute(id);
        return (Student) roomAsyncTask.get();
    }

    //insert

    public void insertStudent(Student student) {
        roomAsyncTask = new RoomAsyncTask(studentDao, RoomAsyncTaskType.INSERTObject);
        roomAsyncTask.execute(student);
    }

}
