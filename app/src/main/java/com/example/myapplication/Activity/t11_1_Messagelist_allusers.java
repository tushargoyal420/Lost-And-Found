package com.example.myapplication.Activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.getuserinlistAdapter;
import com.example.myapplication.Models.User;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class t11_1_Messagelist_allusers extends AppCompatActivity{
    private ImageButton mbacktodash;
    private EditText msearch;
    private RecyclerView mrecyclerviewmessageslist;

    private getuserinlistAdapter userAdapter;
    private List<User> mUsers;
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
        userAdapter = new getuserinlistAdapter(this, mUsers,true);
        mrecyclerviewmessageslist.setAdapter(userAdapter);
    }

    private void readUsers() {
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(com.example.myapplication.Models.User.class);
                    assert user != null;
                    assert firebaseUser != null;

                    if (!user.getId().equals(firebaseUser.getUid())) {
                        mUsers.add(user);
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