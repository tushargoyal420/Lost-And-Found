package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.t2models.Model;
import com.example.myapplication.t5loginsignup.t4_LoginActivity;
import com.example.myapplication.t6found.t8_1_show_found_items;
import com.example.myapplication.t7lost.t7_2_Upload_lost_item;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.protobuf.StringValue;
import com.squareup.picasso.Picasso;

public class itemmoredetails extends AppCompatActivity {
    ImageView mitemimage;
    TextView mitemname, mitemdes, mitemdate, mitemplace, muser,musernameonscreen, msameuser;
    ImageButton mbacktoitemlist;
    Button msendmessage;
    DatabaseReference ref, DataRef;
    FirebaseAuth fAuth;
    private String User;
//    RecyclerView mrecyclerviewitem;
//    FirebaseRecyclerAdapter<Itemdetails, MyViewHolder3> adapter3;
//    FirebaseRecyclerOptions<Itemdetails> options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemmoredetails);
        mbacktoitemlist = findViewById(R.id.backtoitemlist);
        mitemimage = findViewById(R.id.itemimage);
        mitemname = findViewById(R.id.itemname);
        mitemdes = findViewById(R.id.itemdes);
        mitemdate = findViewById(R.id.itemdate);
        mitemplace = findViewById(R.id.itemplace);
//        muser = findViewById(R.id.user);
        msameuser = findViewById(R.id.sameuser);
//        musernameonscreen = findViewById(R.id.usernameonscreen);
        msendmessage = findViewById(R.id.sendmessage222);
        fAuth = FirebaseAuth.getInstance();
        User = fAuth.getCurrentUser().getUid();   //current user

//        mrecyclerviewitem = findViewById(R.id.recyclerviewitem);
//        mrecyclerviewitem.setLayoutManager(new LinearLayoutManager(this));
//            LoadData(ItemKey);
//        ref.child(ItemKey).addValueEventListener(new ValueEventListener() {

//        ref = FirebaseDatabase.getInstance().getReference().child("founditems");
//        mtextViewid.setText(ItemKey);
        data();
    }
    public void data(){
        String ItemKey = getIntent().getStringExtra("ItemKey");
        DataRef = FirebaseDatabase.getInstance().getReference().child("founditems").child(ItemKey);
        DataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
//                    String itemName = dataSnapshot.child("name_of_Item").getValue().toString();
//                    String itemDate = dataSnapshot.child("date").getValue().toString();
//                    String itemDes = dataSnapshot.child("description").getValue().toString();
//                    String itemPlace = dataSnapshot.child("place").getValue().toString();
//                    String muser2 = dataSnapshot.child("user").getValue().toString();
//                    String ImageUrl = dataSnapshot.child("imageUri").getValue().toString();
                    Itemdetails model= dataSnapshot.getValue(Itemdetails.class);
                    mitemname.setText(model.getName_of_Item());
                    mitemdes.setText(model.getDescription());
                    mitemdate.setText(model.getDate());
                    mitemplace.setText(model.getPlace());
                    Picasso.get().load(model.getImageUri()).into(mitemimage);
                    String userdata= model.getUser();
//                    muser.setText(userdata);
//                    musernameonscreen.setText(model.getUser());

                    if(String.valueOf(userdata).equals(String.valueOf(User))){
                        msendmessage.setVisibility(View.INVISIBLE);
                        msameuser.setVisibility(View.VISIBLE);
                    }else{
                        msendmessage.setVisibility(View.VISIBLE);
                        msameuser.setVisibility(View.INVISIBLE);

                        msendmessage.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(itemmoredetails.this, messageAct.class);
//                                String userdata2= "Y1SJTq8uYETM9E8d0kxEfhj9FQ63";
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

//        private void LoadData(String ItemKey) {
//            Query query=DataRef.equalTo(ItemKey );
//            options= new FirebaseRecyclerOptions.Builder<Itemdetails>().setQuery(query, Itemdetails.class).build();
//        adapter3 = new FirebaseRecyclerAdapter<Itemdetails, MyViewHolder3>(options) {
//            @Override
//            protected void onBindViewHolder(@NonNull MyViewHolder3 holder, int position, @NonNull Itemdetails model2) {
//
//                holder.itemname2.setText(model2.getName_of_Item());
//                holder.itemplace2.setText(model2.getPlace());
////                holder.itemdate2.setText(model2.getDate());
//                holder.itemdes2.setText(model2.getDescription());
//                Picasso.get().load(model2.getImageUri()).into(holder.itemimage2);

//                String itemName2 = dataSnapshot.child("name_of_Item").getValue().toString();
//                holder.user2.setText(model.getUser());
//                Object user2 = model.getUser();


//                holder.sendmessage2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent=new Intent(itemmoredetails.this, sendmessage.class);
//                        intent.putExtra("User", String.valueOf(user2));
//                        startActivity(intent);
//                    }
//                });
//            }
//            @NonNull @Override
//            public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdetailcardview,parent, false);
//                return new MyViewHolder3(view);
//            }
//        };
//        adapter3.startListening();
//        mrecyclerviewitem.setAdapter(adapter3);
//    }



//    private void Loaditem() {
//        options3= new FirebaseRecyclerOptions.Builder<Posts>().setQuery(DataRef, Posts.class).build();
//        adapter3 = new FirebaseRecyclerAdapter<Posts, MyViewHolder3>(options3) {
//            @Override
//            protected void onBindViewHolder(@NonNull MyViewHolder3 holder2, int position, @NonNull Posts model) {
//                holder2.itemname2.setText(model.getName_of_Item());
//                holder2.itemplace2.setText(model.getPlace());
//                holder2.itemdate2.setText(model.getDate());
//                holder2.itemdes2.setText(model.getDescription());
//
////                String itemName2 = dataSnapshot.child("name_of_Item").getValue().toString();
////                holder.user2.setText(model.getUser());
////                Object user2 = model.getUser();
//
////                Picasso.get().load(model.getImageUri()).into(holder2.itemimage2);
//
////                holder.sendmessage2.setOnClickListener(new View.OnClickListener() {
////                    @Override
////                    public void onClick(View v) {
////                        Intent intent=new Intent(itemmoredetails.this, sendmessage.class);
////                        intent.putExtra("User", String.valueOf(user2));
////                        startActivity(intent);
////                    }
////                });
//            }
//            @NonNull @Override
//            public MyViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.itemdetailcardview,parent, false);
//                return new MyViewHolder3(view);
//            }
//        };
//        adapter3.startListening();
//        mrecyclerviewitem.setAdapter(adapter3);
//    }
//}
//    }










