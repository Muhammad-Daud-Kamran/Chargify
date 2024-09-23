package com.example.chargify;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;
import android.widget.TextView;

public class BatteryLevelReceiver extends BroadcastReceiver {

    private CircularFillableLoaders circularFillableLoader;
    private TextView batteryPercentageText;

    public BatteryLevelReceiver(CircularFillableLoaders loader, TextView textView) {
        this.circularFillableLoader = loader;
        this.batteryPercentageText = textView;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BATTERY_CHANGED.equals(intent.getAction())) {
            int level = intent.getIntExtra("level", -1);
            int scale = intent.getIntExtra("scale", -1);

            if (level != -1 && scale != -1) {

                int batteryPct = (int) ((level / (float) scale) * 100);


                int invertedProgress = 100 - batteryPct;

                if (circularFillableLoader != null) {

                    circularFillableLoader.setProgress(invertedProgress);
                }

                if (batteryPercentageText != null) {
                    batteryPercentageText.setText(batteryPct + "%");
                }
            }
        }
    }
}
