package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.ItemResult;
import com.example.myapplication.Retrofit.RetrofitHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btnOne);

        btn1.setOnClickListener(view -> {
            RetrofitHelper helper = new RetrofitHelper(this, MainActivity.this::onCallBackComplete);
            helper.getData("getPosts.php");
        });
    }

    private void onCallBackComplete(Object response) {
        List<ItemResult> list = (List<ItemResult>) response;
    }
}
