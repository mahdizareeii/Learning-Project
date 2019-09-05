package com.example.myapplication.JetPack.LiveData;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class GetNumberLiveData extends ViewModel {

    private String TAG = getClass().getSimpleName();
    private MutableLiveData<String> number;

    public MutableLiveData<String> getNumber() {
        if (number == null) {
            number = new MutableLiveData<>();
        }
        return number;
    }

    public void setNumber() {
        number = new MutableLiveData<>();
        Random random = new Random();
        number.setValue("number is" + random.nextInt(((20 - 10) + 1) + 10));
    }
}
