package com.example.myapplication.Utils.Persmission;

import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class RequestPermission {
    private AppCompatActivity appCompatActivity;

    public RequestPermission(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    public void checkPermission(@NonNull String[] permission, int requestCode) {
        StringBuilder a = new StringBuilder();
        a.append("1");
        for (String s : permission)
            if (ContextCompat.checkSelfPermission(appCompatActivity, s) != PackageManager.PERMISSION_GRANTED) {
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
