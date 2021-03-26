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

public class t2_SignupActivity extends AppCompatActivity {
    EditText mfullname, mEmailAddress, mCreatePassword;
    Button mSignUpButton,mAlreadyHaveAnAccount;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t2_activity_signup);

        mfullname = findViewById(R.id.fullname);
        mEmailAddress = findViewById(R.id.EmailAddress);
        mCreatePassword = findViewById(R.id.CreatePassword);
        mAlreadyHaveAnAccount = findViewById(R.id.AlreadyHaveAnAccount);
        mSignUpButton = findViewById(R.id.SignUpButton);

        fAuth = FirebaseAuth.getInstance();     //for take instance from the our firebase

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailAddress.getText().toString().trim();
                String password = mCreatePassword.getText().toString().trim();

                //check is user enter an email id or not
                if (TextUtils.isEmpty(email)) {
                    mEmailAddress.setError("Please enter an Email");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mCreatePassword.setError("Please enter an Password");
                    return;
                }
                if (password.length() < 6) {
                    mCreatePassword.setError("Enter password more then 6 characters");
                    return;
                }

                //registration
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(t2_SignupActivity.this, "User Created", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), t4_WelcomeNote.class));
                            //show user created and move to homepage
                        } else {
                            Toast.makeText(t2_SignupActivity.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //show an error message
                        }
                    }
                });
            }

        });

        mAlreadyHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), t3_LoginActivity.class));
            }
        });

    }
}



