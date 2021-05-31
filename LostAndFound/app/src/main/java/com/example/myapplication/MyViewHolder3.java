package com.example.myapplication;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder3 extends RecyclerView.ViewHolder {
    public ImageView itemimage2;
    public TextView itemname2, itemplace2, itemdes2, itemdate2, user2;
//    public Button sendmessage2;

    public MyViewHolder3(@NonNull View itemdetailscardview) {
        super(itemdetailscardview);

        itemimage2= itemdetailscardview.findViewById(R.id.itemimage2);
        itemname2= itemdetailscardview.findViewById(R.id.itemname2);
        itemplace2= itemdetailscardview.findViewById(R.id.itemplace2);
        itemdes2= itemdetailscardview.findViewById(R.id.itemdes2);
        itemdate2= itemdetailscardview.findViewById(R.id.itemdate2);
        user2= itemdetailscardview.findViewById(R.id.user2);

//        sendmessage2= itemdetailscardview.findViewById(R.id.sendmessage2);

    }
}
