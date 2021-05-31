package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class MyViewHolder extends RecyclerView.ViewHolder{

    public TextView namee;
    public TextView datee;
    public TextView placee;
    public ImageView imagev;
//    public Button contactmessagebut;
public View vi;
public CardView card;
        public MyViewHolder(@NonNull View cardView){
            super(cardView);
            namee = cardView.findViewById(R.id.nameofitem);
            datee = cardView.findViewById(R.id.dateofitem);
            placee = cardView.findViewById(R.id.placeofitem);
            imagev = cardView.findViewById(R.id.imageofitem);
            vi= cardView;
            card = cardView.findViewById(R.id.card);
        }
    }
