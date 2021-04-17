package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class t8_I_lost_something extends AppCompatActivity {
    ImageButton mbackbutlost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t8_activity_i_lost_something);

        mbackbutlost = findViewById(R.id.backbutfound);
        mbackbutlost.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        if (view == mbackbutlost) {
            finish();
        }
    }



}