package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Utils.DownloadTask;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btnOne);
        editText = findViewById(R.id.edtUrl);

        btn1.setOnClickListener(view -> {
            DownloadTask downloadTask = new DownloadTask(MainActivity.this);
            downloadTask.execute(editText.getText().toString());
        });

        /*btn1.setOnClickListener(view -> {
            RetrofitHelper helper = new RetrofitHelper(this, MainActivity.this::onCallBackComplete);
            helper.getData("getPosts.php");
        });*/
    }

    /*private void onCallBackComplete(Object response) {
        List<ItemResult> list = (List<ItemResult>) response;
    }*/
}
