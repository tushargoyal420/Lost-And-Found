package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Models.Itemdetails;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class t9_ParticularItemsDetails extends AppCompatActivity {
    ImageView mitemimage;
    TextView mitemname, mitemdes, mitemdate, mitemplace, msendmessage , muser,musernameonscreen, msameuser;
    ImageButton mbacktofounditemlist;
//    Button msendmessage;
    DatabaseReference ref, DataRef;
    FirebaseAuth fAuth;
    private String User;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t9_activity_particular_items_details);
        mitemimage = findViewById(R.id.itemimage);
        mitemname = findViewById(R.id.itemname);
        mitemdes = findViewById(R.id.itemdes);
        mitemdate = findViewById(R.id.itemdate);
        mitemplace = findViewById(R.id.itemplace);
//        msameuser = findViewById(R.id.sameuser);
        msendmessage = findViewById(R.id.sendmessage222);

        fAuth = FirebaseAuth.getInstance();
        User = fAuth.getCurrentUser().getUid();   //current user
        mbacktofounditemlist = findViewById(R.id.backtofounditemlist);
        mbacktofounditemlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        data();
    }
    public void data(){
        String ItemKey = getIntent().getStringExtra("ItemKey");
        String Item = getIntent().getStringExtra("Item");
        String lost= "lost";
        String found= "found";
        if(Item.equals(found)){
            DataRef = FirebaseDatabase.getInstance().getReference().child("founditems").child(ItemKey);
        }if (Item.equals(lost)){
            DataRef = FirebaseDatabase.getInstance().getReference().child("lostitems").child(ItemKey);
        }
        DataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                 Itemdetails model= dataSnapshot.getValue(Itemdetails.class);
                    mitemname.setText(model.getName_of_Item());
                    mitemdes.setText(model.getDescription());
                    mitemdate.setText(model.getDate());
                    mitemplace.setText(model.getPlace());
                    Picasso.get().load(model.getImageUri()).into(mitemimage);
                    String userdata= model.getUser();
                    String youupload= "You uploaded this item";
                    if(String.valueOf(userdata).equals(String.valueOf(User))){
//                        msendmessage.setVisibility(View.INVISIBLE);
                        msendmessage.setText(youupload);
//                        msameuser.setVisibility(View.VISIBLE);
                    }else{
//                        msendmessage.setVisibility(View.VISIBLE);
//                        msameuser.setVisibility(View.INVISIBLE);
                        msendmessage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(t9_ParticularItemsDetails.this, t10_SendMessageToUser.class);
                                intent.putExtra("userid", model.getUser());
                                startActivity(intent);
                            }
                        });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        });
    }
}
