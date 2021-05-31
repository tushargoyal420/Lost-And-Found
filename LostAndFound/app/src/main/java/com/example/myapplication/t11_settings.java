package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.myapplication.t5loginsignup.t1_Login_signup_choice;
import com.google.firebase.auth.FirebaseAuth;

public class t11_settings extends AppCompatActivity {
    ImageButton mbackbutsettings, mlogoutlistbut, mratinglistbut, mcustomercarebut;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t11_activity_settings);
        fAuth = FirebaseAuth.getInstance();

        mbackbutsettings = findViewById(R.id.backbuttonsettings);
        mbackbutsettings.setOnClickListener(this::onClick);

        mlogoutlistbut = findViewById(R.id.logoutlistbut);
        mlogoutlistbut.setOnClickListener(this::onClick);

        mcustomercarebut = findViewById(R.id.customercarelistbut);
        mcustomercarebut.setOnClickListener(this::onClick);

        mratinglistbut = findViewById(R.id.ratinglistbut);
        mratinglistbut.setOnClickListener(this::onClick);

    }
    public void onClick(View view) {
        if (view == mbackbutsettings) {
            finish();
        } else if (view == mlogoutlistbut) {
            fAuth.signOut();
            finish();
            startActivity(new Intent(this, t1_Login_signup_choice.class));
        }
    }
}