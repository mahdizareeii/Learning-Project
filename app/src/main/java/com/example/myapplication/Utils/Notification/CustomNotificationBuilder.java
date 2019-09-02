package com.example.myapplication.Utils.Notification;

import android.content.Context;

import androidx.annotation.DrawableRes;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class CustomNotificationBuilder {

    private Context context;

    @DrawableRes
    private int icon;

    private String title;

    private String text;

    private String channelId;

    private int progress;

    //
    private NotificationManagerCompat notificationManagerCompat;


    public CustomNotificationBuilder(Context context) {
        this.context = context;
        notificationManagerCompat = NotificationManagerCompat.from(context);
    }

    private void showCustomNotificationBuilder(@DrawableRes int icon, String title, String text, String channelId, int progress) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder.setSmallIcon(icon)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_LOW);

        builder.setProgress(100, progress, false);
        notificationManagerCompat.notify(1, builder.build());

        if (progress == 100) {
            builder.setContentText("Download Complete").setProgress(0, 0, false);
            notificationManagerCompat.notify(1, builder.build());
        }
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
