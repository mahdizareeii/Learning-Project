package com.example.myapplication.Utils.DownloadFileService;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import androidx.annotation.Nullable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Objects;

public class DownloadFileService extends IntentService {

    public static final int PROGRESS_UPDATE = 8344;

    public DownloadFileService() {
        super("DownloadFileService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {

            URL url = new URL(Objects.requireNonNull(intent).getStringExtra("url"));
            ResultReceiver resultReceiver = intent.getParcelableExtra("receiver");

            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("Server returned HTTP " + httpURLConnection.getResponseCode() +
                        httpURLConnection.getResponseMessage());
            }

            int fileLength = httpURLConnection.getContentLength();

            inputStream = httpURLConnection.getInputStream();

            outputStream = new FileOutputStream("/sdcard/" + intent.getStringExtra("fileName"));

            byte[] data = new byte[4096];

            long total = 0;

            int count;

            while ((count = inputStream.read(data)) != -1) {

                total += count;

                if (fileLength > 0) {
                    Bundle resultData = new Bundle();
                    resultData.putInt("progress", (int) (total * 100 / fileLength));
                    Objects.requireNonNull(resultReceiver).send(PROGRESS_UPDATE, resultData);
                }

                outputStream.write(data, 0, count);

            }

            Bundle resultData = new Bundle();
            resultData.putInt("progress", 100);
            Objects.requireNonNull(resultReceiver).send(PROGRESS_UPDATE, resultData);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                if (outputStream != null)
                    outputStream.close();

                if (inputStream != null)
                    inputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (httpURLConnection != null)
                httpURLConnection.disconnect();

        }

    }
}
