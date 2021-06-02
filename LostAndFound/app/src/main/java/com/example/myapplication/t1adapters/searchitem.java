package com.example.myapplication.t1adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.itemmoredetails;
import com.example.myapplication.messageAct;
import com.example.myapplication.t2models.Model;
import com.example.myapplication.t2models.userlist;
import com.example.myapplication.t6found.t8_1_show_found_items;
import com.squareup.picasso.Picasso;

import java.util.List;

public class searchitem extends RecyclerView.Adapter<searchitem.ViewHolder> {

    private Context mContext;
    private List<Model> mUsers;

    public searchitem(Context mContext, List<Model> mUsers) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public searchitem.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.singleview, parent, false);
        return new searchitem.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull searchitem.ViewHolder holder, int position) {
        final Model model = mUsers.get(position);

        holder.itemname.setText(model.getName_of_Item());
        holder.itemplace.setText(model.getPlace());
        holder.itemdate.setText(model.getDate());
        Picasso.get().load(model.getImageUri()).into(holder.itemimage);

//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, messageAct.class);
//                intent.putExtra("userid", userlist.getId());
//                mContext.startActivity(intent);
//            }
//        });
//        holder.card.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String found= "found";
//                Intent intent=new Intent(mContext, itemmoredetails.class);
//                intent.putExtra("ItemKey", model.getKey());
//                intent.putExtra("Item",found);
//                mContext.startActivity(intent);
//            }
//        });
    }

    @Override
    public int getItemCount() {

        return mUsers.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView itemimage;
        public TextView itemname, itemplace, itemdes, itemdate;
        public CardView card;

        public ViewHolder(View itemView) {
            super(itemView);
            itemimage = itemView.findViewById(R.id.imageofitem);
            itemname = itemView.findViewById(R.id.nameofitem);
            itemplace = itemView.findViewById(R.id.placeofitem);
//        itemdes = itemView.findViewById(R.id.desofitem);
            itemdate = itemView.findViewById(R.id.dateofitem);
            card = itemView.findViewById(R.id.card);
        }
    }
}

