package com.example.myapplication.t5loginsignup;

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

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class t2_SignupActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth fAuth;
    private String userid;
    private EditText mfullname, mEmailAddress, mCreatePassword, musername , mphonenumber;
    private Button msSignUpButton, mAlreadyHaveAnAccount;
    private Context context = this;

    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t2_activity_signup);
        mfullname = findViewById(R.id.fullname);
//        musername= findViewById(R.id.username);
        mEmailAddress = findViewById(R.id.EmailAddress);
        mCreatePassword = findViewById(R.id.CreatePassword);
        mphonenumber= findViewById(R.id.phonenumber);

        fAuth = FirebaseAuth.getInstance();     //for take instance from the our firebase
        msSignUpButton = findViewById(R.id.sSignupButton);
        mAlreadyHaveAnAccount = findViewById(R.id.AlreadyHaveAnAccount);

        msSignUpButton.setOnClickListener(this);
        mAlreadyHaveAnAccount.setOnClickListener(this);


    }

    private void registerUser() {
        String name =  mfullname.getText().toString().trim();
//        String username =  musername.getText().toString().trim();
        String email = mEmailAddress.getText().toString().trim();
        String password = mCreatePassword.getText().toString().trim();
        String phnnum =  mphonenumber.getText().toString().trim();

        //check is user enter details or not
//        if (TextUtils.isEmpty(username)) {
//            mCreatePassword.setError("Please enter an username");
//            return;
//        }
        if (TextUtils.isEmpty(email)) {
            mEmailAddress.setError("Please enter an Email");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            mCreatePassword.setError("Please enter an Password");
//            return;
        }

        if (password.length() < 6) {
            mCreatePassword.setError("Enter password more then 6 characters");
//            return;
        }
         if(TextUtils.isEmpty(phnnum)){
            mEmailAddress.setError("Please enter an Phone number");
//            return;
        }
        else {
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
                                     // for realtime database
                                     userid = fAuth.getCurrentUser().getUid();

                                     HashMap<String, String> userMap = new HashMap<>();
                                     userMap.put("id", userid);
                                     userMap.put("Name", name);
                                     userMap.put("Email", email);
//                                     userMap.put("username", username);
                                     userMap.put("Phonenumber", phnnum);

                                     root = db.getReference("users").child(userid);
//                                root.push().setValue(userMap);
                                     root.setValue(userMap);

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
    }

    @Override
    public void onClick(View view) {
        if (view == msSignUpButton) {
            // Register user
            registerUser();
        } else if (view == mAlreadyHaveAnAccount) {
            finish();
            // Redirect to login activity
            startActivity(new Intent(this, t4_LoginActivity.class));
        }
    }
}