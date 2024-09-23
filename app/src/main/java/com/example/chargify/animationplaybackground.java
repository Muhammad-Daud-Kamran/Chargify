package com.example.chargify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class animationplaybackground extends AppCompatActivity {
    private LottieAnimationView animationPreview;
    private TextView tvTime1, tvDate1, tvbatterryprev1;
    private BroadcastReceiver batteryReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animationplaybackground);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainanimbackground), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTime1 = findViewById(R.id.tvTime1);
        tvDate1 = findViewById(R.id.tvDate1);
        tvbatterryprev1 = findViewById(R.id.tvbatterryprev1);
        animationPreview = findViewById(R.id.animationPreview);
        setCurrentTimeAndDate();


        SharedPreferences sharedPreferences = getSharedPreferences("ChargifyPrefs", MODE_PRIVATE);
        int selectedAnimationRes = sharedPreferences.getInt("selectedAnimation", R.raw.electric);

        animationPreview.setAnimation(selectedAnimationRes);
        animationPreview.playAnimation();

        registerBatteryReceiver();
    }
    private void setCurrentTimeAndDate() {

        Date now = new Date();
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mma", Locale.getDefault());
        String formattedTime = timeFormat.format(now);
        tvTime1.setText(formattedTime);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(now);
        tvDate1.setText(formattedDate);
    }
    private void registerBatteryReceiver() {
        batteryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                if (level != -1 && scale != -1) {
                    int batteryPercentage = (int) ((level / (float) scale) * 100);
                    tvbatterryprev1.setText(batteryPercentage + "%");
                }
            }
        };

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryReceiver, filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (batteryReceiver != null) {
            unregisterReceiver(batteryReceiver);
        }
    }
}