package com.example.alarmik;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class AlarmHelper {

    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    public void startAlarm(Context context) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, alarmIntent);
    }

    public void stopAlarm() {
        if (alarmManager != null) {
            alarmManager.cancel(alarmIntent);
        }
    }
}







