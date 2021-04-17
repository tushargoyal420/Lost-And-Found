package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;

public class t6_Dashboard_Activity extends AppCompatActivity implements View.OnClickListener {
    ImageButton mlogoutbut, mmessagesbut, mmyaccountbut, msettingbut;
    Button milostsomethingbut2, mifoundsomethingbut;
    ImageView mlostandfoundimage;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t6_activity_dashboard);
        fAuth = FirebaseAuth.getInstance();
        mifoundsomethingbut = findViewById(R.id.ifoundsomethingbut2);
        milostsomethingbut2 = findViewById(R.id.ilostsomethingbut2);
        mmyaccountbut = findViewById(R.id.myaccountbut);
        mmessagesbut = findViewById(R.id.messagesbut);
        msettingbut = findViewById(R.id.settingbut);
        mlogoutbut = findViewById(R.id.logoutbut);

        mifoundsomethingbut.setOnClickListener(this);
        milostsomethingbut2.setOnClickListener(this);
        mmyaccountbut.setOnClickListener(this);
        mmessagesbut.setOnClickListener(this);
        msettingbut.setOnClickListener(this);
        mlogoutbut.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        if (view == mlogoutbut) {
            fAuth.signOut();
            finish();
            startActivity(new Intent(this, t1_Login_signup_choice.class));
        } else if (view == mifoundsomethingbut) {
//            finish();
            startActivity(new Intent(this, t7_I_found_something.class));
        }
        else if (view == milostsomethingbut2) {
//            finish();
            startActivity(new Intent(this, t8_I_lost_something.class));
        }
        else if (view == mmyaccountbut) {
//            finish();
            startActivity(new Intent(this, t9_My_account.class));
        }
        else if (view == mmessagesbut) {
//            finish();
            startActivity(new Intent(this, t10_Message.class));
        }else if (view == msettingbut) {
//            finish();
            startActivity(new Intent(this, t11_settings.class));
        }
    }
}