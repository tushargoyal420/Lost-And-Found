package com.example.myapplication.Activity;

import android.content.Intent;
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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class t4_LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEmailAddress2, mLogInPassword;
    Button mLogInButton, mDontHaveAnAccount, mResetPassButton;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t4_activity_login);

        fAuth = FirebaseAuth.getInstance();
        mEmailAddress2 = findViewById(R.id.EmailAddress2);
        mLogInPassword = findViewById(R.id.LoginPassword2);
        mDontHaveAnAccount = findViewById(R.id.DontHaveAnAccount);
        mLogInButton = findViewById(R.id.LoginButton);
        mResetPassButton = findViewById(R.id.resetPassButton);

        mLogInButton.setOnClickListener(this);
        mDontHaveAnAccount.setOnClickListener(this);
        mResetPassButton.setOnClickListener(this);
    }
    private void userLogin() {
        String email = mEmailAddress2.getText().toString().trim();
        String password = mLogInPassword.getText().toString().trim();

         if (TextUtils.isEmpty(email)) {
            mEmailAddress2.setError("Please enter an Email");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mLogInPassword.setError("Please enter an Password");
            return;
        }

        fAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            assert user != null;
                            if (!user.isEmailVerified()) {
                                Toast.makeText(t4_LoginActivity.this, "Please Verify email.", Toast.LENGTH_SHORT).show();
                            } else {
                                // start main activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), t6_Dashboard_Activity.class));
                            }
                        } else {
                            Toast.makeText(t4_LoginActivity.this, "Authentication Error", Toast.LENGTH_SHORT).show();
                            //show an error message
                        }
                    }
                });
    }
    @Override
    public void onClick(View view) {
        if (view == mLogInButton) {
            userLogin();
        } else if (view == mDontHaveAnAccount) {
            finish();
            startActivity(new Intent(this, t2_SignupActivity.class));
        } else if (view == mResetPassButton) {
            finish();
            startActivity(new Intent(this, t5_Reset_password.class));
        }
        }
    }