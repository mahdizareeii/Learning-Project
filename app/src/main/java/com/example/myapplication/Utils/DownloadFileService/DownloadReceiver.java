package com.example.myapplication.Utils.DownloadFileService;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class DownloadReceiver extends ResultReceiver {

    private ProgressDialog progressDialog;

    public DownloadReceiver(Handler handler, ProgressDialog progressDialog) {
        super(handler);
        this.progressDialog = progressDialog;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        super.onReceiveResult(resultCode, resultData);

        /*if (!progressDialog.isShowing())
            progressDialog.show();
*/
        if (resultCode == DownloadFileService.PROGRESS_UPDATE) {
            int progress = resultData.getInt("progress");
            /*progressDialog.setIndeterminate(false);
            progressDialog.setMax(100);
            progressDialog.setProgress(progress);*/
            /*if (progress == 100) {
                progressDialog.dismiss();
            }*/
        }

    }
}
