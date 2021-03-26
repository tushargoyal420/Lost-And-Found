package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class t3_LoginActivity extends AppCompatActivity {
    EditText mEmailAddress2, mLogInPassword;
    Button mLogInButton,mDontHaveAnAccount;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t3_activity_login);

        mEmailAddress2 = findViewById(R.id.EmailAddress2);
        mLogInPassword = findViewById(R.id.LogInPassword);
        mDontHaveAnAccount = findViewById(R.id.DontHaveAnAccount);
        mLogInButton = findViewById(R.id.LogInButton);

        mLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailAddress2.getText().toString().trim();
                String password = mLogInPassword.getText().toString().trim();

                //check is user enter an email id or not
                if (TextUtils.isEmpty(email)) {
                    mEmailAddress2.setError("Please enter an Email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mLogInPassword.setError("Please enter an Password");
                    return;
                }
                if (password.length() < 6) {
                    mLogInPassword.setError("Enter password more then 6 characters");
                    return;
                }
                // authenticate
                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(t3_LoginActivity.this, "Loged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), t4_WelcomeNote.class));
                            //show user created and move to homepage
                        } else {
                            Toast.makeText(t3_LoginActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //show an error message
                        }
                    }
                });
            }
        });

        mDontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), t2_SignupActivity.class));

            }
        });

    }

}