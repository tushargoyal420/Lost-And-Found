package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class t8_I_lost_something extends AppCompatActivity {
    ImageButton mbackbutlost, maddlostitembut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t8_activity_i_lost_something);

        mbackbutlost = findViewById(R.id.backbutfound);
        mbackbutlost.setOnClickListener(this::onClick);

        maddlostitembut = findViewById(R.id.addlostitembut);
        maddlostitembut.setOnClickListener(this::onClick);

    }

    public void onClick(View view) {
        if (view == mbackbutlost) {
            finish();
        }
        else if(view== maddlostitembut){
            startActivity(new Intent(this, t8_2_Upload_lost_item.class));
        }
    }



}