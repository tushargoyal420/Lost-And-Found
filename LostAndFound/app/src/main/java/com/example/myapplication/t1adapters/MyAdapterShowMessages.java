package com.example.myapplication.t1adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.t2models.ModelMessages;
import com.example.myapplication.t2models.ModelMessagesList;

import java.util.ArrayList;

public class MyAdapterShowMessages extends RecyclerView.Adapter<MyAdapterShowMessages.MyViewHolder> {

    ArrayList<ModelMessages> mList3;
    Context context;

    public MyAdapterShowMessages(Context context, ArrayList<ModelMessages> mList3){
        this.mList3 = mList3;
        this.context= context;
    }
    @NonNull @Override
    public MyAdapterShowMessages.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.chatcardview, parent, false);
        return new MyAdapterShowMessages.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterShowMessages.MyViewHolder holder, int position) {

        ModelMessages modelMessages= mList3.get(position);
//        holder.username.setText(modelMessagesList.getUsername());
        holder.message.setText(modelMessages.getMessage());
    }
    @Override
    public int getItemCount() {
        return mList3.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView message;
        public MyViewHolder(@NonNull View cardView){
            super(cardView);
            message = cardView.findViewById(R.id.message);
        }
    }
}