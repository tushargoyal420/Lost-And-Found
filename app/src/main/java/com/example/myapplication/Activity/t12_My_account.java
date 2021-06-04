package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.RetreiveItemsIntoList;
import com.example.myapplication.Adapters.RetreiveUserDetails;
import com.example.myapplication.Models.Itemdetails;
import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class t12_My_account extends AppCompatActivity {
    ImageButton mbackfrommyaccount, meditprofile;
    CircleImageView mprofileimage;
    TextView mprofileusername,mprofilephonenumber,mprofileemailaddress;
    DatabaseReference ref, DataRef;
    FirebaseAuth fAuth;
    private String User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t9_activity_my_account);
        mbackfrommyaccount = findViewById(R.id.backfrommyaccount);
        mbackfrommyaccount.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { finish(); }});
        meditprofile = findViewById(R.id.editprofile);
        meditprofile.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) {}});

        mprofileimage= findViewById(R.id.profileimage);
        mprofileusername= findViewById(R.id.profileusername);
        mprofilephonenumber= findViewById(R.id.profilephonenumber);
        mprofileemailaddress= findViewById(R.id.profileemailaddress);
        fAuth = FirebaseAuth.getInstance();
        User = fAuth.getCurrentUser().getUid();   //current user

        LoadUserData();
    }

    private void LoadUserData() {
        String ItemKey = getIntent().getStringExtra("CurrentUser");
        DataRef = FirebaseDatabase.getInstance().getReference().child("users").child(ItemKey);
        DataRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists()) {
                User user= dataSnapshot.getValue(User.class);
//                Picasso.get().load(user.getImageURL()).into(mprofileimage);
                mprofileusername.setText(user.getName());
                mprofileemailaddress.setText(user.getEmail());
                mprofilephonenumber.setText(user.getPhonenumber());
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