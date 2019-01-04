package com.example.webplat.amoldesigning.Activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.webplat.amoldesigning.R;


public class SplashScreen extends AppCompatActivity {

    private static final int RESOLUTION_REQUEST = 9000;
    private static final String TAG = "SplashActivity";
    private static int SPLASH_TIME_OUT = 3000;
    TextView mTexViewTextMessage;
    ImageView mImageSplash;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
        setContentView(R.layout.activity_splash);

        bindViews();
    }

    private void bindViews() {

        mImageSplash = (ImageView) findViewById(R.id.imageSplash);


        Animation rotate = AnimationUtils.loadAnimation(this, R.anim.translate);
        mImageSplash.startAnimation(rotate);

        startTimer();
    }

    public void startTimer() {
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over

                launchHomeScreen();
                finish();

            }
        }, SPLASH_TIME_OUT);

    }


    private void launchHomeScreen() {
        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
        finish();
    }


}
