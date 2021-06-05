package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class t0_Splash_screen extends AppCompatActivity {
    View mpurpleeline, mwhiteline, mredline, mwhiteline2, mgreenline,mpurpleline2;
    TextView mlostandfoubdtextsplashscreen, mwelcomeinourappsplashscreen;
    private static final int SPLASH_TIME_OUT = 4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t0_activity_splash_screen);

        mpurpleeline = findViewById(R.id.purpelline);
        mwhiteline= findViewById(R.id.whiteline);
        mredline= findViewById(R.id.redline);
        mwhiteline2= findViewById(R.id.whiteline2);
        mgreenline= findViewById(R.id.greenline);
        mpurpleline2= findViewById(R.id.purpelline2);
        mlostandfoubdtextsplashscreen= findViewById(R.id.lostandfoubdtextsplashscreen);
        mwelcomeinourappsplashscreen= findViewById(R.id.welcomeinourappsplashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( t0_Splash_screen.this, t1_Login_signup_choice.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
