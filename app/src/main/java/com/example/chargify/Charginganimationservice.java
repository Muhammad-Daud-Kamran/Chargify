package com.example.chargify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.os.IBinder;

public class Charginganimationservice extends Service {
    private BroadcastReceiver chargingReceiver;

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "ChargingAnimation",
                    "Charging Animation",
                    NotificationManager.IMPORTANCE_LOW
            );
            NotificationManager manager = getSystemService(NotificationManager.class);
            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }


        Notification notification = new Notification.Builder(this, "ChargingAnimation")
                .setContentTitle("Charging Animation")
                .setContentText("Animation is playing on the lock screen")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(Notification.PRIORITY_LOW)
                .build();

        

        chargingReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
                boolean isCharging = (status == BatteryManager.BATTERY_STATUS_CHARGING || status == BatteryManager.BATTERY_STATUS_FULL);

                if (isCharging) {
                    Intent previewIntent = new Intent(context, animationplaybackground.class);
                    previewIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK );
                    startActivity(previewIntent);
                }
            }
        };


        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(chargingReceiver, filter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chargingReceiver != null) {
            unregisterReceiver(chargingReceiver);
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
