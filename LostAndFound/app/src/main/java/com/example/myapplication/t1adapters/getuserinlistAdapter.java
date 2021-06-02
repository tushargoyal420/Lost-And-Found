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
import com.example.myapplication.t2models.ChatMessage;
import com.example.myapplication.t2models.userlist;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class getuserinlistAdapter extends RecyclerView.Adapter<getuserinlistAdapter.ViewHolder> {

    private Context mContext;
    private List<userlist> mUsers;
    private boolean ischat;

    String theLastMessage;

    public getuserinlistAdapter(Context mContext, List<userlist> mUsers, boolean ischat) {
        this.mUsers = mUsers;
        this.mContext = mContext;
        this.ischat = ischat;
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
        holder.profile_image.setImageResource(R.mipmap.profileicon);

        if (ischat){
            lastMessage(userlist.getId(), holder.last_msg);
        }
        else {
            holder.last_msg.setVisibility(View.VISIBLE);
        }

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
//        public ImageView profile_image;
        public CircleImageView profile_image;
//        private ImageView img_on;
//        private ImageView img_off;

        public ViewHolder(View itemView) {
            super(itemView);

            username = itemView.findViewById(R.id.usernameinlist);
            last_msg = itemView.findViewById(R.id.lastmessege);
            profile_image = itemView.findViewById(R.id.userimageinlist);

//            img_on = userchat_card.findViewById(R.id.img_on);
//            img_off = userchat_card.findViewById(R.id.img_off);
//            last_msg = userchat_card.findViewById(R.id.lastmessege);
        }
    }

    private void lastMessage(String userid, TextView last_msg){
        theLastMessage = "default";
        final FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Chats");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ChatMessage chat = snapshot.getValue(ChatMessage.class);
                    if (firebaseUser != null && chat != null) {
                        if (chat.getReceiver().equals(firebaseUser.getUid()) && chat.getSender().equals(userid) ||
                                chat.getReceiver().equals(userid) && chat.getSender().equals(firebaseUser.getUid())) {
                            theLastMessage = chat.getMessage();
                        }
                    }
                }

                switch (theLastMessage){
                    case  "default":
                        last_msg.setText("");
                        break;

                    default:
                        last_msg.setText(theLastMessage);
                        break;
                }

                theLastMessage = "default";
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }}