package com.example.myapplication.Utils.Notification;

import android.app.Notification;
import android.content.Context;
import android.widget.RemoteViews;

import androidx.annotation.DrawableRes;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.myapplication.R;

public class NotificationBuilder {

    private Context context;
    private int icon;
    private String title;
    private String text;
    private String channelId;

    public NotificationBuilder(Context context) {
        this.context = context;
    }

    private void showNotification(@DrawableRes int icon, String title, String text, String channelId) {
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

        Notification notification = new NotificationCompat.Builder(context, channelId)
                .setContentTitle(title)
                .setContentText(text)
                .setSmallIcon(icon)
                .build();

        notificationManagerCompat.notify(1, notification);
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
