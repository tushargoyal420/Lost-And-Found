package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

public class t1_Login_signup_choice extends AppCompatActivity {
    Button msignUpButtonSS, mlogInButton2SS ;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t1_activity_login_signup_choice);

        mlogInButton2SS = findViewById(R.id.loginbutton2);
        msignUpButtonSS = findViewById(R.id.signupbutton);

        fAuth = FirebaseAuth.getInstance();     //for take instance from the our firebase

        if (fAuth.getCurrentUser() != null) {             //if user is already login
            startActivity(new Intent(getApplicationContext(), t6_Dashboard_Activity.class));
            finish();
        }

        mlogInButton2SS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), t4_LoginActivity.class));
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