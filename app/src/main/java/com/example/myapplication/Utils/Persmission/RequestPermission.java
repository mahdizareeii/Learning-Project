package com.example.myapplication.Utils.Persmission;

import android.app.Application;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.Utils.BaseActivity;

public class RequestPermission {
    AppCompatActivity appCompatActivity;

    public RequestPermission(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void checkPermission(@NonNull String[] permission, int requestCode) {
        StringBuilder a = new StringBuilder();
        a.append("1");
        for (int i = 0; i < permission.length; i++)
            if (ContextCompat.checkSelfPermission(appCompatActivity, permission[i]) != PackageManager.PERMISSION_GRANTED) {
                a.append("0");
            }
        if (a.toString().contains("0")) {
            requestPermission(permission, requestCode);
        }
    }

    private void requestPermission(String[] permission, int requestCode) {
        ActivityCompat.requestPermissions(appCompatActivity, permission, requestCode);
    }

    //on Request Permission Result is in BaseActivity
}
