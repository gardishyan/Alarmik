package com.example.alarmik;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ALM", "Received alarm, use intent to pass data");
        NotificationHelper.show(context, "Alarmik", "Notification from alarm");
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.cartoon);
        mediaPlayer.setOnCompletionListener(MediaPlayer::release);
        mediaPlayer.start();
    }
}
