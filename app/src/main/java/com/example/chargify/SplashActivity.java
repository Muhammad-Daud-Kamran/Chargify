package com.example.chargify;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.airbnb.lottie.LottieAnimationView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private LottieAnimationView lottieAnimationView;
    private TextView tvchargify;
    private ProgressBar progressBar;
    private Button btncontinueonboard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        lottieAnimationView = findViewById(R.id.splashlottie);
        tvchargify = findViewById(R.id.tvchargify);
        progressBar = findViewById(R.id.progressBarsplash);

        btncontinueonboard = findViewById(R.id.btncontinueonboard);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setProgress(100);
                progressBar.setMax(100);
                fadeOutProgressBar();
                btncontinueonboard.setVisibility(View.VISIBLE);
            }
        }, 3000);

        btncontinueonboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentsplash = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intentsplash);
                finish();
            }
        });

    }
    private void fadeOutProgressBar() {
        ObjectAnimator fadeOut = ObjectAnimator.ofFloat(progressBar, "alpha", 1f, 0f);
        fadeOut.setDuration(1000);
        fadeOut.start();

        fadeOut.addListener(new android.animation.AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(android.animation.Animator animation) {
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
