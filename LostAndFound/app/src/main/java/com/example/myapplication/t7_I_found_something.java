package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class t7_I_found_something extends AppCompatActivity {
    ImageButton mbackbutfound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t7_activity_i_found_something);

        mbackbutfound = findViewById(R.id.backbutfound);
        mbackbutfound.setOnClickListener(this::onClick);
    }
    public void onClick(View view) {
        if (view == mbackbutfound) {
            finish();
        }
    }

}