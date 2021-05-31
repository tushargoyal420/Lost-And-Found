package com.example.myapplication.t1adapters;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.itemmoredetails;
import com.example.myapplication.t2models.Model;
import com.example.myapplication.t6found.t8_1_show_found_items;
import com.example.myapplication.t6found.t8_2_upload_found_item;
import com.squareup.picasso.Picasso;
import com.firebase.ui.database.FirebaseRecyclerAdapter;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<Model> mList;
    Context context;

    public MyAdapter(Context context, ArrayList<Model> mList){
        this.mList = mList;
        this.context= context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder,final int position) {
        Model model = mList.get(position);
        holder.namee.setText(model.getName_of_Item());
        holder.datee.setText(model.getDate());
        holder.placee.setText(model.getPlace());
        Picasso.get().load(model.getImageUri()).into(holder.imagev);

//        holder.card.setTag(position);
//        holder.contactmessagebut.setText(model.getPhone());
//
//        holder.v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(context, itemmoredetails.class);
//                intent.putExtra("Itemkey",getRef(position).getKey());
//                context.startActivity(intent);

//                String value = (String) v.getItemAtPosition(position);
//                intent.putExtra(itemmoredetails.EXTRA_POST_KEY, postKey);
//            }
//        });

//        final String date= model.getDate();
//        final String des= model.getDescription();
//        final String itemname= model.getName_of_Item();
//        final String imageuri= model.getImageUri();
//        final String place= model.getPlace();
//        holder.v.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(), itemmoredetails.class);
//                startActivity(intent);
//            }
//        });
//        holder.card.setOnClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String value = (String) holder.card.setTag(position);
//                //Toast.makeText(Inventory.this,value,Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, itemmoredetails.class);
//                intent.putExtra("Itemname",value);
//                context.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView namee, datee, placee;
        ImageView imagev;
        Button contactmessagebut;
        View v;
        CardView card;

        public MyViewHolder(@NonNull View cardView){
            super(cardView);
            namee = cardView.findViewById(R.id.nameofitem);
            datee = cardView.findViewById(R.id.dateofitem);
            placee = cardView.findViewById(R.id.placeofitem);
            imagev = cardView.findViewById(R.id.imageofitem);
//            contactmessagebut = cardView.findViewById(R.id.contactmessagebut);
//            v= cardView;
            card = cardView.findViewById(R.id.card);
        }
    }
}
