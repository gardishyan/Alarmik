package com.example.alarmik;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Random;

public class NotificationHelper {

    public static void createChannel(Context context) {
        NotificationChannel channel = new NotificationChannel("alarmik_channel_id", "Alarmik Channel", NotificationManager.IMPORTANCE_HIGH);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC); // to display on locked screen

        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);
    }

    public static void show(Context context, String title, String content) {
        if (context.checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

            //Any activity here
            Intent intent = new Intent(context, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "my_channel_id")
                    .setSmallIcon(R.drawable.pomidor)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setContentIntent(pendingIntent)
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            Random random = new Random();
            int notificationId = random.nextInt(1000);
            notificationManager.notify(notificationId, builder.build());
        }
    }
}
