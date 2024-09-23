package com.example.chargify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class batteryinfo extends AppCompatActivity {
    ImageView imgback;
     TextView batteryHealth, batteryStatus, batteryLevel, batteryChargingType, batteryVoltage, batteryTemperature, batteryTechnology;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_batteryinfo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgback=findViewById(R.id.imgback);
        batteryHealth = findViewById(R.id.battery_health);
        batteryStatus = findViewById(R.id.battery_status);
        batteryLevel = findViewById(R.id.battery_level);
        batteryChargingType = findViewById(R.id.battery_charging_type);
        batteryVoltage = findViewById(R.id.battery_voltage);
        batteryTemperature = findViewById(R.id.battery_temperature);
        batteryTechnology = findViewById(R.id.battery_technology);
        imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext=new Intent(batteryinfo.this,MainActivityhome2.class);
                startActivity(inext);
            }
        });
        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, ifilter);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryReceiver);
    }

    private final BroadcastReceiver batteryReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN);
            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int voltage = intent.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);
            int temperature = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
            String technology = intent.getStringExtra(BatteryManager.EXTRA_TECHNOLOGY);

            // Update UI
            batteryHealth.setText(getBatteryHealthString(health));
            batteryStatus.setText(getBatteryStatusString(status));
            batteryLevel.setText(level + "%");
            batteryChargingType.setText(getChargingTypeString(status));
            batteryVoltage.setText((voltage / 1000) + "V"); // Convert to volts
            batteryTemperature.setText((temperature / 10) + "°C"); // Convert to Celsius
            batteryTechnology.setText(technology);
        }
    };

    private String getBatteryHealthString(int health) {
        switch (health) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                return "Good";
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                return "Overheat";
            case BatteryManager.BATTERY_HEALTH_DEAD:
                return "Dead";
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                return "Over Voltage";
            case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
                return "Unspecified Failure";
            default:
                return "Unknown";
        }
    }

    private String getBatteryStatusString(int status) {
        switch (status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                return "Charging";
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                return "Discharging";
            case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
                return "Not Charging";
            case BatteryManager.BATTERY_STATUS_FULL:
                return "Full";
            default:
                return "Unknown";
        }
    }

    private String getChargingTypeString(int status) {
        if (status == BatteryManager.BATTERY_STATUS_CHARGING) {
            int chargePlug = getIntent().getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            if (chargePlug == BatteryManager.BATTERY_PLUGGED_USB) {
                return "USB";
            } else if (chargePlug == BatteryManager.BATTERY_PLUGGED_AC) {
                return "AC Adapter";
            } else {
                return "Unknown";
            }
        } else {
            return "None";
        }
    }
}