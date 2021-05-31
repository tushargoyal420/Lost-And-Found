package com.example.myapplication.t1adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.t2models.Model;
import com.example.myapplication.t2models.ModelMessagesList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyAdapterShowMessagesList extends RecyclerView.Adapter<MyAdapterShowMessagesList.MyViewHolder> {

    ArrayList<ModelMessagesList> mList2;
    Context context;

    public MyAdapterShowMessagesList(Context context, ArrayList<ModelMessagesList> mList2){
        this.mList2 = mList2;
        this.context= context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.chatcardview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ModelMessagesList modelMessagesList= mList2.get(position);
//        holder.username.setText(modelMessagesList.getUsername());
        holder.username.setText(modelMessagesList.getPlace());
    }
    @Override
    public int getItemCount() {
        return mList2.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        public MyViewHolder(@NonNull View cardView){
            super(cardView);
            username = cardView.findViewById(R.id.nameofuser);
        }
    }
}