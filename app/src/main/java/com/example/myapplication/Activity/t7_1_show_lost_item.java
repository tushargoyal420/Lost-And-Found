package com.example.myapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.RetreiveItemsIntoList;
import com.example.myapplication.Models.Itemdetails;
import com.example.myapplication.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
public class t7_1_show_lost_item extends AppCompatActivity {

    private ImageButton mbacktodashfromlost, maddlostitembut;
    private EditText minputSearchLost;
    private RecyclerView mrecyclerViewlost;

    private FirebaseRecyclerAdapter<Itemdetails, RetreiveItemsIntoList> adapter2;
    private FirebaseRecyclerOptions<Itemdetails> options;
    private  DatabaseReference PostRef= FirebaseDatabase.getInstance().getReference().child("lostitems");

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
                startActivity(new Intent(t7_1_show_lost_item.this,t7_2_Upload_lost_item.class));
            }
        });

        mrecyclerViewlost = findViewById(R.id.recyclerviewlost);
        mrecyclerViewlost.setLayoutManager(new LinearLayoutManager(this));

        minputSearchLost=findViewById(R.id.minputSearchFound);
        LoadPost();
    }
    private void LoadPost() {
        options= new FirebaseRecyclerOptions.Builder<Itemdetails>().setQuery(PostRef, Itemdetails.class).build();
        adapter2 = new FirebaseRecyclerAdapter<Itemdetails, RetreiveItemsIntoList>(options) {
            @Override
            protected void onBindViewHolder(@NonNull RetreiveItemsIntoList holder, int position, @NonNull Itemdetails itemdetails) {
                holder.itemname.setText(itemdetails.getName_of_Item());
                holder.itemplace.setText(itemdetails.getPlace());
                holder.itemdate.setText(itemdetails.getDate());

                Picasso.get()
                        .load(itemdetails.getImageUri())
                        .into(holder.itemimage);

                holder.card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String lost= "lost";
                        Intent intent=new Intent(t7_1_show_lost_item.this, t9_ParticularItemsDetails.class);
                        intent.putExtra("ItemKey",getRef(position).getKey());
                        intent.putExtra("Item",lost);
                        startActivity(intent);
                    }
                });
            }
            @NonNull
            @Override
            public RetreiveItemsIntoList onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item_in_list,parent, false);
                return new RetreiveItemsIntoList(view);
            }
        };
        adapter2.startListening();
        mrecyclerViewlost.setAdapter(adapter2);
    }
}