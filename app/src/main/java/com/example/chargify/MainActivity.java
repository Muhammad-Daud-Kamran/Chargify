package com.example.chargify;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {
    ViewPager slideviewpager;
    LinearLayout indicatorlayout;
    Button btnnext, btnback, btnskip;

    TextView[] dots;
    vpAdapter vpageradapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.onboarlayout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnnext = findViewById(R.id.btnnext);
        btnback = findViewById(R.id.btnback);
        btnskip= findViewById(R.id.btnskip);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0) > 0) {

                    slideviewpager.setCurrentItem(getItem(-1), true);
                }

            }
        });
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getItem(0) < 2) {
                    slideviewpager.setCurrentItem(getItem(1), true);
                }
                else {
                    Intent intentnxt = new Intent(MainActivity.this, mainactivityhome.class);
                    startActivity(intentnxt);
                    finish();
                }

            }
        });
        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent skipintent = new Intent(MainActivity.this, mainactivityhome.class);
                startActivity(skipintent);
                finish();
            }

        });
        slideviewpager = (ViewPager) findViewById(R.id.slideviewpager);
        indicatorlayout = (LinearLayout) findViewById(R.id.indicatorlayout);
        vpageradapter = new vpAdapter(this);
        slideviewpager.setAdapter(vpageradapter);
        setupindicator(0);
        slideviewpager.addOnPageChangeListener(viewlistener);


    }

    public void setupindicator(int position) {
        dots = new TextView[3];
        indicatorlayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.bright_cyan));
            indicatorlayout.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.text_soft_white, getApplicationContext().getTheme()));
    }

    ViewPager.OnPageChangeListener viewlistener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
setupindicator(position);
if (position>0){
    btnback.setVisibility(View.VISIBLE);
}
else {
    btnback.setVisibility(View.INVISIBLE);
}
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
    private int getItem(int i) {
        return slideviewpager.getCurrentItem() + i;
    }
}
