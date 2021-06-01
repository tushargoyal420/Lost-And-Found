package com.example.myapplication.t1adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.messageAct;
import com.example.myapplication.t2models.ChatMessage;
import com.example.myapplication.t2models.userlist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class ChatMessageRetreive extends RecyclerView.Adapter<ChatMessageRetreive.ViewHolder> {

    private Context mContext;
    private List<ChatMessage> mchat;
    private String imageurl;
    public static final int MSG_TYPE_LEFT= 0;
    public static final int MSG_TYPE_RIGHT= 1;
    FirebaseUser fuser;

    public ChatMessageRetreive(Context mContext, List<ChatMessage> mchat, String imageurl) {
        this.mchat = mchat;
        this.mContext = mContext;
        this.imageurl= imageurl;
    }

    @NonNull
    @Override
    public ChatMessageRetreive.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == MSG_TYPE_RIGHT) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chatrightside, parent, false);
            return new ChatMessageRetreive.ViewHolder(view);
        } else {
            View view = LayoutInflater.from(mContext).inflate(R.layout.chatleftside, parent, false);
            return new ChatMessageRetreive.ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ChatMessageRetreive.ViewHolder holder2, int position) {

        ChatMessage chat = mchat.get(position);
        holder2.show_message.setText(chat.getMessage());

//        if (imageurl.equals("default")){
//            holder2.profile_image.setImageResource(R.mipmap.ic_launcher);
//        } else {
//            Glide.with(mContext).load(imageurl).into(holder.profile_image);
//        }
//        if (position == mchat.size()-1){
//            if (chat.isIsseen()){
//                holder.txt_seen.setText("Seen");
//            } else {
//                holder.txt_seen.setText("Delivered");
//            }
//        } else {
//            holder.txt_seen.setVisibility(View.GONE);
//        }
    }

    @Override
    public int getItemCount() {
        return mchat.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView show_message;
        public ImageView profile_image;
//        public TextView txt_seen;

        public ViewHolder(View itemView) {
            super(itemView);
            show_message = itemView.findViewById(R.id.chatmessage22);
            profile_image = itemView.findViewById(R.id.userprofileimage);
//            txt_seen = itemView.findViewById(R.id.txt_seen);
        }
    }

    @Override
    public int getItemViewType(int position) {
        fuser = FirebaseAuth.getInstance().getCurrentUser();
        if (mchat.get(position).getSender().equals(fuser.getUid())){
            return MSG_TYPE_RIGHT;
        } else {
            return MSG_TYPE_LEFT;
        }
    }
}

