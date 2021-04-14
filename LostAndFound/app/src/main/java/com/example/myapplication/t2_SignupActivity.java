package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class t2_SignupActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth fAuth;
    EditText mfullname, mEmailAddress, mCreatePassword;
    Button mSignUpButton, mAlreadyHaveAnAccount;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t3_activity_signup);
        mfullname = findViewById(R.id.fullname);
        mEmailAddress = findViewById(R.id.EmailAddress);
        mCreatePassword = findViewById(R.id.CreatePassword);
        fAuth = FirebaseAuth.getInstance();     //for take instance from the our firebase
        mSignUpButton = findViewById(R.id.SignUpButton);
        mAlreadyHaveAnAccount = findViewById(R.id.AlreadyHaveAnAccount);

        mSignUpButton.setOnClickListener(this);
        mAlreadyHaveAnAccount.setOnClickListener(this);
    }

    private void registerUser() {
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
                    FirebaseUser user = fAuth.getCurrentUser();
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "successful", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    finish();
                    startActivity(new Intent(getApplicationContext(), t3_Signup_congratulation.class));
                } else {
                    Toast.makeText(t2_SignupActivity.this, "Could not registered. Please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view == mSignUpButton) {
            // Register user
            registerUser();
        } else if (view == mAlreadyHaveAnAccount) {
            finish();
            // Redirect to login activity
            startActivity(new Intent(this, t4_LoginActivity.class));
        }
    }
}