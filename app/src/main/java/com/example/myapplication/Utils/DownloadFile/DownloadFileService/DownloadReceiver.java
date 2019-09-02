package com.example.myapplication.Utils.DownloadFile.DownloadFileService;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.example.myapplication.R;
import com.example.myapplication.Utils.Notification.CustomNotificationBuilder;
import com.example.myapplication.Utils.Notification.NotificationBuilder;

public class DownloadReceiver extends ResultReceiver {

    private CustomNotificationBuilder customNotificationBuilder;
    private int lastProgress = -1;


    public DownloadReceiver(Context context, Handler handler) {
        super(handler);
        customNotificationBuilder = new CustomNotificationBuilder(context);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);

        if (resultCode == DownloadFileService.PROGRESS_UPDATE) {
            int progress = resultData.getInt("progress");
            if (progress != lastProgress) {
                customNotificationBuilder
                        .setChannelId("n")
                        .setIcon(R.drawable.ic_launcher_background)
                        .setTitle("Downloading")
                        .setText("File")
                        .setProgress(progress).build();
                lastProgress = progress;
            }
        }
    }
}
