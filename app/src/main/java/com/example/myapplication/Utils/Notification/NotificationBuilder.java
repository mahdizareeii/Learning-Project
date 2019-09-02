package com.example.myapplication.Utils.Notification;

import android.content.Context;

import androidx.annotation.DrawableRes;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationBuilder {

    private Context context;
    private int icon;
    private String title;
    private String text;
    private String channelId;

    //
    private NotificationManagerCompat notificationManagerCompat;


    public NotificationBuilder(Context context) {
        this.context = context;
        notificationManagerCompat = NotificationManagerCompat.from(context);
    }

    private void showNotification(@DrawableRes int icon, String title, String text, String channelId) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, channelId);
        builder.setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(icon)
                .setPriority(NotificationCompat.PRIORITY_LOW);


        notificationManagerCompat.notify(1, builder.build());
    }

    public NotificationBuilder setIcon(@DrawableRes int icon) {
        this.icon = icon;
        return this;
    }

    public NotificationBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public NotificationBuilder setText(String text) {
        this.text = text;
        return this;
    }

    public NotificationBuilder setChannelId(String channelId) {
        this.channelId = channelId;
        return this;
    }

    public NotificationBuilder build() {
        showNotification(icon, title, text, channelId);
        return this;
    }
}
