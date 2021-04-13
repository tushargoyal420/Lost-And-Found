package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
import com.google.firebase.auth.FirebaseUser;

public class t3_LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText mEmailAddress2, mLogInPassword;
    Button mLogInButton, mDontHaveAnAccount, mResetPassButton;
    FirebaseAuth fAuth;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t3_activity_login);

        fAuth = FirebaseAuth.getInstance();
        mEmailAddress2 = findViewById(R.id.EmailAddress2);
        mLogInPassword = findViewById(R.id.LogInPassword);
        mDontHaveAnAccount = findViewById(R.id.DontHaveAnAccount);
        mLogInButton = findViewById(R.id.LogInButton);
        mResetPassButton = findViewById(R.id.resetPassButton);


        mLogInButton.setOnClickListener(this);
        mDontHaveAnAccount.setOnClickListener(this);
        mResetPassButton.setOnClickListener(this);
    }

    private void userLogin() {
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
//                if (password.length() < 6) {
//                    mLogInPassword.setError("Enter password more then 6 characters");
//                    return;
//                }
        // authenticate
        fAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = fAuth.getCurrentUser();
                            if (!user.isEmailVerified()) {
                                Toast.makeText(t3_LoginActivity.this, "Please Verify email.", Toast.LENGTH_SHORT).show();
                            } else {
                                // start main activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), t4_WelcomeNote.class));
                            }
                        } else {
                            Toast.makeText(t3_LoginActivity.this, "Authentication Error", Toast.LENGTH_SHORT).show();
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
            // Reset password through email
            fAuth.getInstance().sendPasswordResetEmail("tusshartg420@gmail.com")
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(context, "Email Sent", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }
}


//
//        mDontHaveAnAccount.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(getApplicationContext(), t2_SignupActivity.class));
//
//            }
//        });
//
//    }
//
//}