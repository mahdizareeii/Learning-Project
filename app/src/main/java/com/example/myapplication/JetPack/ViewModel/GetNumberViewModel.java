package com.example.myapplication.JetPack.ViewModel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.Random;

public class GetNumberViewModel extends ViewModel {

    private String TAG = getClass().getSimpleName();
    private String number;

    public String getNumber() {
        Log.i(TAG, "getNumber");
        if (number == null) {
            generateNumber();
        }
        return number;
    }

    private void generateNumber() {
        Random random = new Random();
        number = "Number is : " + random.nextInt(((20 - 10) + 1) + 10);
    }

}
