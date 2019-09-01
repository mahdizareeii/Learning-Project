package com.example.myapplication.Utils.Notification;

import android.app.Notification;
import android.content.Context;
import android.widget.RemoteViews;

import androidx.annotation.DrawableRes;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.R;

public class CustomNotificationBuilder {

    private Context context;

    @DrawableRes
    private int icon;

    private String title;

    private String text;

    private String channelId;

    private int progress;

    public CustomNotificationBuilder(Context context) {
        this.context = context;
    }

    private void showCustomNotificationBuilder(@DrawableRes int icon, String title, String text, String channelId, int progress) {

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.notification);

        remoteViews.setProgressBar(R.id.progressBar, 100, progress, false);

        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(text)
                .setCustomContentView(remoteViews)
                .setCustomBigContentView(remoteViews)
                //.setCustomBigContentView()
                .build();

        notificationManagerCompat.notify(1, notification);

    }

    public CustomNotificationBuilder setIcon(@DrawableRes int icon) {
        this.icon = icon;
        return this;
    }

    public CustomNotificationBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public CustomNotificationBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public CustomNotificationBuilder setChannelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public CustomNotificationBuilder setProgress(int progress) {
        this.progress = progress;
        return this;
    }

    public CustomNotificationBuilder build() {
        showCustomNotificationBuilder(icon, title, text, channelId, progress);
        return this;
    }

}
