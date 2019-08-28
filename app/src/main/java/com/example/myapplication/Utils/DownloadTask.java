package com.example.myapplication.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.PowerManager;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadTask extends AsyncTask<String, Integer, String> {

    private Context context;
    private PowerManager.WakeLock wakeLock;
    private ProgressDialog progressDialog;
    private String fileName;

    public DownloadTask(Context context, String fileName, ProgressDialog progressDialog) {
        this.context = context;
        this.fileName = fileName;
        this.progressDialog = progressDialog;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        PowerManager powerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, getClass().getName());
        wakeLock.acquire(10 * 60 * 1000L /*10 minutes*/);
        progressDialog.show();
    }


    @Override
    protected String doInBackground(String... urls) {

        InputStream inputStream = null;
        OutputStream outputStream = null;
        HttpURLConnection httpURLConnection = null;

        try {

            URL url = new URL(urls[0]);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();

            if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return "Server returned HTTP " + httpURLConnection.getResponseCode() +
                        httpURLConnection.getResponseMessage();
            }

            int fileLength = httpURLConnection.getContentLength();

            inputStream = httpURLConnection.getInputStream();

            outputStream = new FileOutputStream("/sdcard/" + fileName);

            byte[] data = new byte[4096];

            long total = 0;

            int count;

            while ((count = inputStream.read(data)) != -1) {

                if (isCancelled()) {
                    inputStream.close();
                    return null;
                }

                total += count;

                if (fileLength > 0)
                    publishProgress((int) (total * 100 / fileLength));

                outputStream.write(data, 0, count);

            }

        } catch (Exception e) {
            return e.getMessage();
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

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setIndeterminate(false);
        progressDialog.setMax(100);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(String s) {
        wakeLock.release();
        progressDialog.dismiss();
        if (s != null)
            Toast.makeText(context, "Download Failed : " + s, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(context, "File Downloaded", Toast.LENGTH_SHORT).show();
    }

}
