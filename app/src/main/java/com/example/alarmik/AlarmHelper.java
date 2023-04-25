package com.example.alarmik;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

public class AlarmHelper {

    private AlarmManager alarmManager;
    private PendingIntent alarmIntent;

    public void setAlarmInNextMinute(Context context) {
        alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReceiver.class);
        alarmIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.cancel(alarmIntent);
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.S || alarmManager.canScheduleExactAlarms()) {
            //Setting alarm in 17 seconds
            long msToOff = System.currentTimeMillis() + 17000;
            alarmManager.setAlarmClock(new AlarmManager.AlarmClockInfo(msToOff, null), alarmIntent);
//            alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), AlarmManager.INTERVAL_FIFTEEN_MINUTES, alarmIntent);
        }
    }

    public void stopAlarm() {
        if (alarmManager != null) {
            alarmManager.cancel(alarmIntent);
        }
    }
}







