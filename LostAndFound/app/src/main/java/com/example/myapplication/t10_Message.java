package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.t1adapters.MyAdapterShowMessagesList;
import com.example.myapplication.t1adapters.getuserinlistAdapter;
import com.example.myapplication.t2models.ModelMessagesList;
import com.example.myapplication.t2models.User;
import com.example.myapplication.t2models.userlist;
import com.example.myapplication.t7lost.t7_1_show_lost_item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class t10_Message extends AppCompatActivity{
    private ImageButton mbacktodash;
    private EditText msearch;
    private RecyclerView mrecyclerviewmessageslist;

    private getuserinlistAdapter userAdapter;
    private List<userlist> mUsers;
    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t10_activity_message);
        msearch = findViewById(R.id.search_users);

        mrecyclerviewmessageslist = findViewById(R.id.recyclerviewmessageslist);

        mUsers = new ArrayList<>();
        mbacktodash= findViewById(R.id.backtodash2);
        mbacktodash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mrecyclerviewmessageslist.setHasFixedSize(true);
        mrecyclerviewmessageslist.setLayoutManager(new LinearLayoutManager(this));
        readUsers();
        userAdapter = new getuserinlistAdapter(this, mUsers);
        mrecyclerviewmessageslist.setAdapter(userAdapter);
//        mrecyclerviewmessageslist.setLayoutManager(new LinearLayoutManager(this));
//        list2 = new ArrayList<>();
//        adapterShowMessagesList = new MyAdapterShowMessagesList(this, list2);
//        mrecyclerviewmessageslist.setAdapter(adapterShowMessagesList);

//        root.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    ModelMessagesList model2= dataSnapshot.getValue(ModelMessagesList.class);
//                    list2.add(model2);
//                }
//                adapterShowMessagesList.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
    }

    private void readUsers() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    userlist userlist = snapshot.getValue(com.example.myapplication.t2models.userlist.class);
                    assert userlist != null;
                    assert firebaseUser != null;

                    if (!userlist.getId().equals(firebaseUser.getUid())) {
                        mUsers.add(userlist);
                    }
                }
                userAdapter.notifyDataSetChanged();
//                userAdapter = new getuserinlistAdapter(mContext, mUsers);
//                mrecyclerviewmessageslist.setAdapter(userAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }}