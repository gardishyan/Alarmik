package com.example.alarmik;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.alarmik.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    final int REQUEST_CODE = 100; //any integer

    private ActivityMainBinding binding;
    private AlarmHelper alarmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //View binding stuff
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Create notification channel for app
        NotificationHelper.createChannel(this);

        //Create notification permission for new androids
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (checkSelfPermission(android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{android.Manifest.permission.POST_NOTIFICATIONS}, REQUEST_CODE);
            }
        }

        //Display plain notification on click
        binding.btnShowNotification.setOnClickListener(v -> {
            NotificationHelper.show(this, "Alarmik", "Notification from manual click");
        });

        //Set alarm, close app and expect wake up in 1 minute
        alarmHelper = new AlarmHelper();
        binding.btnSetAlarm.setOnClickListener(v -> {
            alarmHelper.setAlarmInNextMinute(this);
            Toast.makeText(this, getString(R.string.msg_alarm_ok), Toast.LENGTH_SHORT).show();
            finish();
        });
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.i("ALM", "Permission granted to display notifications");
        } else {
            Log.w("ALM", "Permission denied to display notifications");
        }
    }
}
