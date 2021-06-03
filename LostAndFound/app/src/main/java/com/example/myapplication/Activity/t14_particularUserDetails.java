package com.example.myapplication.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Models.Itemdetails;
import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class t14_particularUserDetails extends AppCompatActivity {
    ImageButton mbackfromuserprofile;
    CircleImageView mprofileimage;
    TextView musername,mphonenumber,memailaddress;
    DatabaseReference ref, DataRef;
    FirebaseAuth fAuth;
    private String User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t14_activity_particular_user_details);
        mbackfromuserprofile = findViewById(R.id.backfromuserprofile);
        mbackfromuserprofile.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { finish(); }});
        mprofileimage= findViewById(R.id.profileimage);
        musername= findViewById(R.id.username);
        mphonenumber= findViewById(R.id.phonenumber);
        memailaddress= findViewById(R.id.emailaddress);
        LoadUserData();
    }

    private void LoadUserData() {
        String openuserid = getIntent().getStringExtra("openuserid");
        DataRef = FirebaseDatabase.getInstance().getReference().child("users").child(openuserid);
        DataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    com.example.myapplication.Models.User user= dataSnapshot.getValue(User.class);
                    Picasso.get().load(user.getImageURL()).into(mprofileimage);
                    musername.setText(user.getName());
                    memailaddress.setText(user.getEmail());
                    mphonenumber.setText(user.getPhonenumber());
//                String userdata= user.getUser();

//                if(String.valueOf(userdata).equals(String.valueOf(User))){
//                    msendmessage.setVisibility(View.INVISIBLE);
//                    msameuser.setVisibility(View.VISIBLE);
//                }else{
//                    msendmessage.setVisibility(View.VISIBLE);
//                    msameuser.setVisibility(View.INVISIBLE);
//
//                    msendmessage.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(t12_My_account.this, t10_SendMessageToUser.class);
//                            intent.putExtra("userid", user.getUser());
//                            startActivity(intent);
//                        }
//                    });
//                }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}