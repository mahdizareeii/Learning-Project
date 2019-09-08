package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.JetPack.LiveData.GetNumberLiveData;
import com.example.myapplication.JetPack.Room.Student;
import com.example.myapplication.JetPack.Room.StudentInitializer;
import com.example.myapplication.JetPack.ViewModel.GetNumberViewModel;
import com.example.myapplication.Models.ItemResult;
import com.example.myapplication.Retrofit.RetrofitHelper;
import com.example.myapplication.Utils.DownloadFile.DownloadFileAsyncTask.DownloadTask;
import com.example.myapplication.Utils.DownloadFile.DownloadFileService.DownloadFileService;
import com.example.myapplication.Utils.DownloadFile.DownloadFileService.DownloadReceiver;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button btn1, btnTwo;
    private EditText editText;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        editText = findViewById(R.id.edtUrl);

        btn1.setOnClickListener(view -> {
           getStudentByStudentId("256");
        });


    }


    //******************** Jet Pack
    private void getNumberLiveData() {
        GetNumberLiveData getNumberLiveData = ViewModelProviders.of(this).get(GetNumberLiveData.class);
        LiveData<String> getNumber = getNumberLiveData.getNumber();

        getNumber.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setNumberLiveData() {
        GetNumberLiveData getNumberLiveData = ViewModelProviders.of(this).get(GetNumberLiveData.class);
        getNumberLiveData.setNumber();
    }

    private void getNumber() {
        GetNumberViewModel getNumberViewModel = ViewModelProviders.of(this).get(GetNumberViewModel.class);
        Toast.makeText(this, getNumberViewModel.getNumber(), Toast.LENGTH_SHORT).show();
    }

    private void insertData(String name, String lastName, String studentId) {
        StudentInitializer studentInitializer = ViewModelProviders.of(this).get(StudentInitializer.class);
        studentInitializer.insertStudent(new Student(name, lastName, studentId));
    }

    private void getStudentList() {
        StudentInitializer studentInitializer = ViewModelProviders.of(this).get(StudentInitializer.class);
        List<Student> list = null;
        try {
            list = studentInitializer.getStudentList();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Toast.makeText(this, list.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void getStudentListLiveData() {
        StudentInitializer studentInitializer = ViewModelProviders.of(MainActivity.this).get(StudentInitializer.class);
        studentInitializer.getStudentListLiveData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                for (int i = 0; i < students.size(); i++) {
                    Toast.makeText(MainActivity.this, students.get(i).getId() + " | " + students.get(i).getId() + " | " + students.get(i).getName() + " | " + students.get(i).getStudentId(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void getStudentById(int id) {
        StudentInitializer studentInitializer = ViewModelProviders.of(this).get(StudentInitializer.class);
        String name = null;
        try {
            name = studentInitializer.getStudentById(1).getName();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }

    private void getStudentByStudentId(String id) {
        StudentInitializer studentInitializer = ViewModelProviders.of(this).get(StudentInitializer.class);
        String name = null;
        try {
            name = studentInitializer.getStudentByStudentId(id).getName();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, name, Toast.LENGTH_SHORT).show();
    }
    //******************** /Jet Pack


    //******************** DownloadFile
    private void downloadFileWithService(String url) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);

        Intent intent = new Intent(this, DownloadFileService.class);
        intent.putExtra("url", url);
        intent.putExtra("fileName", fileName);
        intent.putExtra("receiver", new DownloadReceiver(this, new Handler()));

        startService(intent);
    }

    private void downloadFileWithAsyncTask(String url) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(fileName);
        progressDialog.setIndeterminate(true);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setCancelable(true);

        DownloadTask downloadTask = new DownloadTask(MainActivity.this, fileName, progressDialog);
        downloadTask.execute(url);

        progressDialog.setOnCancelListener(dialogInterface -> {
            downloadTask.cancel(true);
        });

    }
    //******************** /DownloadFile


    //******************** Retrofit
    private void sendRequestToServer() {
        RetrofitHelper helper = new RetrofitHelper(this, MainActivity.this::onCallBackComplete);
        helper.getData("getPosts.php");
    }

    private void onCallBackComplete(Object response) {
        List<ItemResult> list = (List<ItemResult>) response;
    }
    //******************** /Retrofit


}
