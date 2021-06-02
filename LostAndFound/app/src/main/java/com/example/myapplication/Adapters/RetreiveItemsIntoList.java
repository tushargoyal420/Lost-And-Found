package com.example.myapplication.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class RetreiveItemsIntoList extends RecyclerView.ViewHolder {
    public ImageView itemimage;
    public TextView itemname, itemplace, itemdes, itemdate;
    public CardView card;

    public RetreiveItemsIntoList(@NonNull View itemView) {
        super(itemView);
        itemimage = itemView.findViewById(R.id.imageofitem);
        itemname = itemView.findViewById(R.id.nameofitem);
        itemplace = itemView.findViewById(R.id.placeofitem);
//        itemdes = itemView.findViewById(R.id.desofitem);
        itemdate = itemView.findViewById(R.id.dateofitem);
        card = itemView.findViewById(R.id.card);
    }
}










