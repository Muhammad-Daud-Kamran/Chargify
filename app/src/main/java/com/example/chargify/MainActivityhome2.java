package com.example.chargify;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;

public class MainActivityhome2 extends AppCompatActivity {

    private ImageView imgback;
    private CardView cardsettings,cardselectanim;
    private CircularFillableLoaders circularFillableLoader;
    Switch switch1;
    private TextView batteryPercentageText;
    private BatteryLevelReceiver batteryLevelReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_activityhome2);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        cardsettings = findViewById(R.id.cardsettings);
        cardselectanim = findViewById(R.id.cardselectanim);
        imgback = findViewById(R.id.imgback);
        circularFillableLoader = findViewById(R.id.circularFillableLoader);
        batteryPercentageText = findViewById(R.id.batteryPercentageText);
         switch1= findViewById(R.id.switch1);

        imgback.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivityhome2.this, mainactivityhome.class);
            startActivity(intent);
        });
        cardselectanim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iselect=new Intent(MainActivityhome2.this,animationselection.class);
                startActivity(iselect);
            }
        });

          cardsettings.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivityhome2.this, batteryinfo.class);
            startActivity(intent);
        });


        batteryLevelReceiver = new BatteryLevelReceiver(circularFillableLoader, batteryPercentageText);
        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(batteryLevelReceiver, filter);


        switch1.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {

                Intent serviceIntent = new Intent(MainActivityhome2.this, Charginganimationservice.class);
                startService(serviceIntent);
            } else {

                Intent serviceIntent = new Intent(MainActivityhome2.this, Charginganimationservice.class);
                stopService(serviceIntent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(batteryLevelReceiver);
    }
}
