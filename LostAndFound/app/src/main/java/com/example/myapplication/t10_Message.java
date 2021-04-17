package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class t10_Message extends AppCompatActivity {
    ImageButton mbackbutmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t10_activity_message);

        mbackbutmessage = findViewById(R.id.backbutmessage);
        mbackbutmessage.setOnClickListener(this::onClick);
    }
    public void onClick(View view) {
        if (view == mbackbutmessage) {
            finish();
        }
    }

}
