package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.myapplication.chats.MainActivity;
import com.example.myapplication.t1adapters.MyAdapterShowMessagesList;
import com.example.myapplication.t2models.ModelMessagesList;
import com.example.myapplication.t7lost.t7_1_show_lost_item;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class t10_Message extends AppCompatActivity {
    private RecyclerView mrecyclerviewmessageslist;
    private FirebaseDatabase db= FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("founditems");
    private MyAdapterShowMessagesList adapterShowMessagesList;
    private ArrayList<ModelMessagesList> list2;
    private Button msendmessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t10_activity_message);
        msendmessage = findViewById(R.id.sendmessage);
        msendmessage.setOnClickListener(this::onClick);
        mrecyclerviewmessageslist = findViewById(R.id.recyclerviewmessageslist);
        mrecyclerviewmessageslist.setLayoutManager(new LinearLayoutManager(this));
        list2 = new ArrayList<>();
        adapterShowMessagesList = new MyAdapterShowMessagesList(this, list2);
        mrecyclerviewmessageslist.setAdapter(adapterShowMessagesList);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ModelMessagesList model2= dataSnapshot.getValue(ModelMessagesList.class);
                    list2.add(model2);
                }
                adapterShowMessagesList.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    private void onClick(View view) {
        if (view == msendmessage) {
            startActivity(new Intent(this, sendmessage.class));
        }
    }
}