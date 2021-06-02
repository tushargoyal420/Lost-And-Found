package com.example.myapplication.t7lost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.MyViewHolder2;
import com.example.myapplication.Posts;
import com.example.myapplication.R;
import com.example.myapplication.itemmoredetails;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
public class t7_1_show_lost_item extends AppCompatActivity {

    private ImageButton mbacktodashfromlost, maddlostitembut;
    private EditText minputSearchLost;
    private RecyclerView mrecyclerViewlost;
    private FirebaseRecyclerAdapter<Posts, MyViewHolder2> adapter2;
    private FirebaseRecyclerOptions<Posts> options;
    private  DatabaseReference PostRef= FirebaseDatabase.getInstance().getReference().child("lostitems");;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t7_1_activity_show_lost_item);
//Buttons
        mbacktodashfromlost = findViewById(R.id.backtodashfromlost);
        mbacktodashfromlost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        maddlostitembut = findViewById(R.id.addlostitembut);
        maddlostitembut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(t7_1_show_lost_item.this,t7_2_Upload_lost_item.class));
            }
        });

//RecyclerView
        mrecyclerViewlost = findViewById(R.id.recyclerviewlost);
        mrecyclerViewlost.setLayoutManager(new LinearLayoutManager(this));

        minputSearchLost=findViewById(R.id.minputSearchFound);
        LoadPost();
//        root.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
//                    Model model= dataSnapshot.getValue(Model.class);
//                    list.add(model);
//                }
//                adapter.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
    }
    private void LoadPost() {
        options= new FirebaseRecyclerOptions.Builder<Posts>().setQuery(PostRef, Posts.class).build();
        adapter2 = new FirebaseRecyclerAdapter<Posts, MyViewHolder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder2 holder, int position, @NonNull Posts model) {
                holder.itemname.setText(model.getName_of_Item());
                holder.itemplace.setText(model.getPlace());
                holder.itemdate.setText(model.getDate());
                Picasso.get().load(model.getImageUri()).into(holder.itemimage);

                holder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String lost= "lost";
                        Intent intent=new Intent(t7_1_show_lost_item.this, itemmoredetails.class);
                        intent.putExtra("ItemKey",getRef(position).getKey());
                        intent.putExtra("Item",lost);
                        startActivity(intent);
                    }
                });
            }
            @NonNull
            @Override
            public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview,parent, false);
                return new MyViewHolder2(view);
            }
        };
        adapter2.startListening();
        mrecyclerViewlost.setAdapter(adapter2);
    }
}