package com.example.myapplication.Retrofit;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myapplication.Interfaces.OnCallBackComplete;
import com.example.myapplication.Models.ItemData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitHelper {

    private Context context;
    private OnCallBackComplete onCallBackComplete;

    public RetrofitHelper(Context context, OnCallBackComplete onCallBackComplete) {
        this.context = context;
        this.onCallBackComplete = onCallBackComplete;
    }

    public void getData(String url) {
        Retrofit retrofit = RetrofitClient.getRetrofitClient();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        Call<Object> call = retrofitApi.getData(url);

        call.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call, @NonNull Response<Object> response) {

                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        ItemData itemData = (ItemData) response.body();
                        if (itemData.getStatus().equals("success")) {
                            if (itemData.getResult() != null && itemData.getResult().size() != 0)
                                onCallBackComplete.onCompleted(itemData.getResult());
                        }
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<Object> call, @NonNull Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
