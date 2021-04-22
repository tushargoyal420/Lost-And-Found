package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class t8_1_show_lost_item extends AppCompatActivity {

    ImageButton mbacktouploadlostbutton, maddlostitembut;

    private RecyclerView mrecyclerView;
    private FirebaseDatabase db= FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("lostitems");
    private MyAdapter adapter;

    private ArrayList<Model> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t8_1_activity_show_lost_item);

        mrecyclerView = findViewById(R.id.recyclerviewfound);

        mbacktouploadlostbutton = findViewById(R.id.backtouploadlost);
        mbacktouploadlostbutton.setOnClickListener(this::onClick);
        maddlostitembut = findViewById(R.id.addlostitembut);
        maddlostitembut.setOnClickListener(this::onClick);

        mrecyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new MyAdapter(this, list);
        mrecyclerView.setAdapter(adapter);

        root.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Model model= dataSnapshot.getValue(Model.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
    public void onClick(View view) {
        if (view == mbacktouploadlostbutton) {
            finish();
        }else if (view == maddlostitembut){
            startActivity(new Intent(this, t8_2_Upload_lost_item.class));
        }
    }

}