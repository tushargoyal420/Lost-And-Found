package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class t9_My_account extends AppCompatActivity {
    ImageButton mbackbutmyaccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t9_activity_my_account);
        mbackbutmyaccount = findViewById(R.id.backbutmyaccount);
        mbackbutmyaccount.setOnClickListener(this::onClick);
    }
    public void onClick(View view) {
        if (view == mbackbutmyaccount) {
            finish();
        }
    }

}