package com.example.myapplication;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Utils.DownloadTask;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private EditText editText;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = findViewById(R.id.btnOne);
        editText = findViewById(R.id.edtUrl);


        btn1.setOnClickListener(view -> {

            String text = editText.getText().toString();
            String fileName = text.substring(text.lastIndexOf('/') + 1);

            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(fileName);
            progressDialog.setIndeterminate(true);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setCancelable(true);

            DownloadTask downloadTask = new DownloadTask(MainActivity.this, fileName, progressDialog);
            downloadTask.execute(editText.getText().toString());

            progressDialog.setOnCancelListener(dialogInterface -> {
                downloadTask.cancel(true);
            });

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
