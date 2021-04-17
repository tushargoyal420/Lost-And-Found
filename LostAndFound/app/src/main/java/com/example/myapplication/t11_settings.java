package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class t11_settings extends AppCompatActivity {
    ImageButton mbackbutsettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t11_activity_settings);

        mbackbutsettings = findViewById(R.id.backbutsettings);
        mbackbutsettings.setOnClickListener(this::onClick);
    }
    public void onClick(View view) {
        if (view == mbackbutsettings) {
            finish();
        }
    }

}