package com.example.myapplication.Utils.DownloadFile.DownloadFileService;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.example.myapplication.R;
import com.example.myapplication.Utils.Notification.CustomNotificationBuilder;

public class DownloadReceiver extends ResultReceiver {

    private Context context;

    public DownloadReceiver(Context context, Handler handler) {
        super(handler);
        this.context = context;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);

        if (resultCode == DownloadFileService.PROGRESS_UPDATE) {
            int progress = resultData.getInt("progress");
            new CustomNotificationBuilder(context)
                    .setChannelId("id")
                    .setTitle("Downloading")
                    .setText("klasdklaskd")
                    .setProgress(progress)
                    .build();
        }

    }
}
