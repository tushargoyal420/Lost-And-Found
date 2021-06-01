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

import com.example.myapplication.R;
import com.example.myapplication.messageAct;
import com.example.myapplication.t2models.userlist;

import java.util.List;

public class getuserinlistAdapter extends RecyclerView.Adapter<getuserinlistAdapter.ViewHolder> {

    private Context mContext;
    private List<userlist> mUsers;

    public getuserinlistAdapter(Context mContext, List<userlist> mUsers) {
        this.mUsers = mUsers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.userchatcard, parent, false);
        return new getuserinlistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final userlist userlist = mUsers.get(position);
        holder.username.setText(userlist.getName());
        holder.profile_image.setImageResource(R.mipmap.ic_launcher);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, messageAct.class);
                intent.putExtra("userid", userlist.getId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        return mUsers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView username, last_msg;
        public ImageView profile_image;
//        private ImageView img_on;
//        private ImageView img_off;

        public ViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.usernameinlist);
            profile_image = itemView.findViewById(R.id.userimageinlist);

//            img_on = userchat_card.findViewById(R.id.img_on);
//            img_off = userchat_card.findViewById(R.id.img_off);
//            last_msg = userchat_card.findViewById(R.id.lastmessege);
        }}


}
