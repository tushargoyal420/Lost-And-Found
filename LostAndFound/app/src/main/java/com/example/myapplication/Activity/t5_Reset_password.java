package com.example.myapplication.Activity;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class t5_Reset_password extends AppCompatActivity implements View.OnClickListener {
    Button mresetbutton, mbackbutton;
    EditText mresetEmailAddress;
    FirebaseAuth fAuth;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t5_activity_reset_password);

        fAuth = FirebaseAuth.getInstance();
        mresetbutton = findViewById(R.id.resetbutton2);
        mbackbutton = findViewById(R.id.backbutton);
        mresetEmailAddress = findViewById(R.id.resetEmailAddress);

        mresetbutton.setOnClickListener(this);
        mbackbutton.setOnClickListener(this);
    }

    private void userLogin() {
        String resetmail = mresetEmailAddress.getText().toString().trim();
        if (TextUtils.isEmpty(resetmail)) {
            mresetEmailAddress.setError("Please enter an Email");
            return;
        }

        FirebaseAuth.getInstance().sendPasswordResetEmail(resetmail)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Email Sent", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(context, "Please enter a valid email address", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {
        if (view == mresetbutton) {
            userLogin();
        } else if (view == mbackbutton) {
            finish();
//            startActivity(new Intent(this, t1_Login_signup_choice.class));
        }
    }
}