package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;

import de.hdodenhof.circleimageview.CircleImageView;

public class t6_Dashboard_Activity extends AppCompatActivity implements View.OnClickListener {
    CircleImageView mmessagesbut, mmyaccountbut, msettingbut;
    Button milostsomethingbut2, mifoundsomethingbut2;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t6_activity_dashboard);
        fAuth = FirebaseAuth.getInstance();
        mifoundsomethingbut2 = findViewById(R.id.ifoundsomethingbut2);
        mifoundsomethingbut2.setOnClickListener(this);

        milostsomethingbut2 = findViewById(R.id.ilostsomethingbut2);
        milostsomethingbut2.setOnClickListener(this);

        mmyaccountbut = findViewById(R.id.myaccountbut);
        mmyaccountbut.setOnClickListener(this);

        mmessagesbut = findViewById(R.id.messagesbut);
        mmessagesbut.setOnClickListener(this);

        msettingbut = findViewById(R.id.settingbut);
        msettingbut.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == milostsomethingbut2) {
            startActivity(new Intent(this, t7_1_show_lost_item.class));
        } else if (view == mifoundsomethingbut2) {
            startActivity(new Intent(this, t8_1_show_found_items.class));
        } else if (view == mmyaccountbut) {
            startActivity(new Intent(t6_Dashboard_Activity.this, t12_My_account.class));
        }
        else if (view == mmessagesbut) {
            startActivity(new Intent(this, t11_2_Messagelist_recentusers.class));
        }
        else if (view == msettingbut) {
            startActivity(new Intent(this, t13_settings.class));

        }
    }
}