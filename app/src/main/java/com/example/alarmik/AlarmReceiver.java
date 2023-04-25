package com.example.alarmik;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("ALM", "Received alarm, passed data using intent");
        NotificationHelper.show(context, "Alarmik", "Notification from alarm");
    }
}
