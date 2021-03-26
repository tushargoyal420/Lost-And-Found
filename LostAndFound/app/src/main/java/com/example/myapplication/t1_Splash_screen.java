package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class t1_Splash_screen extends AppCompatActivity {
    Button msignUpButtonSS, mlogInButton2SS ;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t1_activity_splash_screen);

        mlogInButton2SS = findViewById(R.id.logInButton2SS);
        msignUpButtonSS = findViewById(R.id.signUpButtonSS);

        fAuth = FirebaseAuth.getInstance();     //for take instance from the our firebase

        if (fAuth.getCurrentUser() != null) {             //if user is already login
            startActivity(new Intent(getApplicationContext(), t4_WelcomeNote.class));
            finish();
        }

        mlogInButton2SS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), t3_LoginActivity.class));
            }
        });

        msignUpButtonSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), t2_SignupActivity.class));
            }
        });

    }
}