package com.example.chargify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Animationpreview extends AppCompatActivity {
    private LottieAnimationView animationPreview;
    private TextView tvTime, tvDate,tvbatterryprev;
    private Button btnApply;
    private int selectedAnimationRes;
    private BroadcastReceiver batteryReceiver;
    private SharedPreferences sharedPreferences;
    GestureDetector gestureDetector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_animationpreview);
        sharedPreferences = getSharedPreferences("ChargifyPrefs", MODE_PRIVATE);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainpreview), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tvbatterryprev = findViewById(R.id.tvbatterryprev);
        tvTime = findViewById(R.id.tvTime);
        tvDate = findViewById(R.id.tvDate);
        animationPreview = findViewById(R.id.animationPreview);
        btnApply = findViewById(R.id.btnApply);


        setCurrentTimeAndDate();
        Intent intent = getIntent();
        selectedAnimationRes = intent.getIntExtra("animationRes", R.raw.electric);






        animationPreview.setAnimation(selectedAnimationRes);


        registerBatteryReceiver();
        btnApply.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt("selectedAnimation", selectedAnimationRes);
            editor.apply();
            Intent intent1 = new Intent(Animationpreview.this, MainActivityhome2.class);
            startActivity(intent1);
            finish();
            Toast.makeText(this, "Animation applied", Toast.LENGTH_SHORT).show();
        });
    }
    private void setCurrentTimeAndDate() {

        Date now = new Date();

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mma ", Locale.getDefault());
        String formattedTime = timeFormat.format(now);
        tvTime.setText(formattedTime);


        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(now);
        tvDate.setText(formattedDate);
    }
    private void registerBatteryReceiver() {
        batteryReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
                int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
                int batteryPercentage = (int) ((level / (float) scale) * 100);
                tvbatterryprev.setText(batteryPercentage + "%");
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
    @Override
    public void onBackPressed() {
finish();
        super.onBackPressed();
    }

}