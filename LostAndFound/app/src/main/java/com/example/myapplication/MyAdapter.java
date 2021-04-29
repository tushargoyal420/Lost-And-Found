package com.example.myapplication;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import com.squareup.picasso.Target;

import org.w3c.dom.Text;

import java.net.URI;
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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Model model= mList.get(position);
        holder.namee.setText(model.getName_of_Item());
        holder.datee.setText(model.getDate());
        holder.placee.setText(model.getPlace());

        Picasso.get().load(model.getImageUri()).into(holder.imagev);

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView namee, datee, placee;
        ImageView imagev;

        public MyViewHolder(@NonNull View cardView){
            super(cardView);
            namee = cardView.findViewById(R.id.nameofitem);
            datee = cardView.findViewById(R.id.dateofitem);
            placee = cardView.findViewById(R.id.placeofitem);
            imagev = cardView.findViewById(R.id.imageofitem);


        }
    }
}
