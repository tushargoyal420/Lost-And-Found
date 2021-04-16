package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class t5_HomeScreeen extends AppCompatActivity {
    Button  milostsomethingbutton ,mifoundsomethingbutton , mmessagesbuttonhomescreen ;
    ImageButton mlogoutbuttonhomescreen , mmyaccountshomescreen ,msettingbuttonhomescreen;
    ImageView mbackgroundboxhomescreen;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t5_activity_home_screeen);
        mlogoutbuttonhomescreen = findViewById(R.id.logoutbuttonhomescreen);
        milostsomethingbutton = findViewById(R.id.ilostsomethingbutton);
        mifoundsomethingbutton = findViewById(R.id.ifoundsomethingbutton);
        mmyaccountshomescreen = findViewById(R.id.myaccountshomescreen);
        mmessagesbuttonhomescreen = findViewById(R.id.messagesbuttonhomescreen);
        msettingbuttonhomescreen = findViewById(R.id.settingbuttonhomescreen);

        milostsomethingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), t6_ILostSomethingActivity.class));
            }
        });
        mifoundsomethingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), t7_IFoundSomethingActivity.class));
            }
        });
        mmyaccountshomescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), t8_MyAccountAcivity.class));
            }
        });
        mmessagesbuttonhomescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), t9_MessageActivity.class));
            }
        });
        msettingbuttonhomescreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), t10_MySettingActivity.class));
            }
        });

    }
    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), t1_Login_signup_choice.class));
        finish();
    }
}