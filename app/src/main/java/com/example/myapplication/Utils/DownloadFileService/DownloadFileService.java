package com.example.myapplication.Utils.DownloadFileService;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Objects;

public class DownloadFileService extends IntentService {

    public static final int PROGRESS_UPDATE = 1642;

    public DownloadFileService() {
        super("DownloadService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        String urlToDownload = Objects.requireNonNull(intent).getStringExtra("url");
        ResultReceiver resultReceiver = intent.getParcelableExtra("receiver");

        try {
            URL url = new URL(urlToDownload);
            URLConnection urlConnection = url.openConnection();
            urlConnection.connect();

            int fileLength = urlConnection.getContentLength();

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

            String path = "/sdcard/" + intent.getStringExtra("fileName");
            OutputStream outputStream = new FileOutputStream(path);

            byte[] data = new byte[1024];
            long total = 0;
            int count;

            while ((count = inputStream.read(data)) != -1) {

                total += count;
                Bundle resultData = new Bundle();
                resultData.putInt("progress", (int) (total * 100 / fileLength));
                Objects.requireNonNull(resultReceiver).send(PROGRESS_UPDATE, resultData);
                outputStream.write(data, 0, count);

            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Bundle resultData = new Bundle();
        resultData.putInt("progress", 100);
        Objects.requireNonNull(resultReceiver).send(PROGRESS_UPDATE, resultData);

    }
}
