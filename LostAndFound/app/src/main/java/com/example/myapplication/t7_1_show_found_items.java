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

public class t7_1_show_found_items extends AppCompatActivity {

    ImageButton mbacktouploadfoundbutton, maddfounditembut;
    private RecyclerView mrecyclerViewfound;
    private FirebaseDatabase db= FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("founditems");
    private MyAdapter adapter;

    private ArrayList<Model> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t7_1_show_found_items);

        mbacktouploadfoundbutton = findViewById(R.id.backtouploadfound);
        mbacktouploadfoundbutton.setOnClickListener(this::onClick);
        maddfounditembut = findViewById(R.id.addfounditembut);
        maddfounditembut.setOnClickListener(this::onClick);

        mrecyclerViewfound = findViewById(R.id.recyclerviewfound);

        mrecyclerViewfound.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new MyAdapter(this, list);
        mrecyclerViewfound.setAdapter(adapter);

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
        if (view == mbacktouploadfoundbutton) {
            finish();
        }else if (view == maddfounditembut){
            startActivity(new Intent(this, t7_2_upload_found_item.class));
        }
    }
}