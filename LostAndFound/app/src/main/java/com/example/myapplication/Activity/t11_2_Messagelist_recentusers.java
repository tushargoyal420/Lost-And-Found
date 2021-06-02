package com.example.myapplication.Activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Adapters.getuserinlistAdapter;
import com.example.myapplication.Models.SendAndReceiveMessage;
import com.example.myapplication.Models.User;
import com.example.myapplication.Notifications.Token;
import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.reactivex.annotations.NonNull;

public class t11_2_Messagelist_recentusers extends AppCompatActivity {
    private ImageButton mbacktodash2222;
    private RecyclerView recyclerviewmessageslist222;
    EditText msearch_users;
    private getuserinlistAdapter userAdapter;
    private List<User> mUsers;

    private List<String> Lists;

    FirebaseUser fuser;
    DatabaseReference reference,reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t10_2_message_list_activity_);
        mbacktodash2222= findViewById(R.id.backtodash2222);
        mbacktodash2222.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        recyclerviewmessageslist222 = findViewById(R.id.recyclerviewmessageslist222);
        recyclerviewmessageslist222.setHasFixedSize(true);
        recyclerviewmessageslist222.setLayoutManager(new LinearLayoutManager(this));
//        userAdapter = new getuserinlistAdapter(this, mUsers);
//        recyclerviewmessageslist222.setAdapter(userAdapter);
        fuser = FirebaseAuth.getInstance().getCurrentUser();
//         mUsers= new ArrayList<>();
//        usersList = new ArrayList<>();
//        userAdapter = new getuserinlistAdapter(this, mUsers);
//        recyclerviewmessageslist222.setAdapter(userAdapter);

        msearch_users =findViewById(R.id.search_users);
        msearch_users.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchUsers(charSequence.toString().toLowerCase());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        Lists = new ArrayList<>();

        reference = FirebaseDatabase.getInstance().getReference("Chats");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Lists.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                    SendAndReceiveMessage chat = snapshot.getValue(SendAndReceiveMessage.class);
                    assert chat != null;
                    if (chat.getSender().equals(fuser.getUid())) {
                        Lists.add(chat.getReceiver());
                    }
                    if (chat.getReceiver().equals(fuser.getUid())) {
                        Lists.add(chat.getSender());
                    }

                    Set<String> hashSet = new HashSet<String>(Lists);
                    Lists.clear();
                    Lists.addAll(hashSet);
//                    Chatlist chatlist = snapshot.getValue(Chatlist.class);
//                    usersList.add(chatlist);
                }
                chatListf();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        updateToken(String.valueOf(FirebaseMessaging.getInstance().getToken()));
//        updateToken(FirebaseInstanceId.getInstance().getToken());
        updateToken(FirebaseInstanceId.getInstance().getToken());
//        updateToken(FirebaseMessagingService);
    }
    private void updateToken(String token){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
        Token token1 = new Token(token);
        reference.child(fuser.getUid()).setValue(token1);
    }


    private void searchUsers(String s) {
        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("users").orderByChild("Name").startAt(s).endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    User user = snapshot.getValue(User.class);

                    assert user != null;
                    assert fuser != null;
                    if (!user.getId().equals(fuser.getUid())){
                        mUsers.add(user);
                    }
                }

//                userAdapter = new UserAdapter(getContext(), mUsers, false);
//                recyclerView.setAdapter(userAdapter);
                userAdapter = new getuserinlistAdapter(t11_2_Messagelist_recentusers.this, mUsers,true);
                recyclerviewmessageslist222.setAdapter(userAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void chatListf() {
        mUsers = new ArrayList<>();
        reference2 = FirebaseDatabase.getInstance().getReference("users");
        reference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    for (String id : Lists) {
                        assert user != null;
                        if (user.getId().equals(id)) {
//                            if (mUsers.size() != 0) {
//                                for (User user1 : mUsers) {
//                                    if (!user.getId().equals(user1.getId())) {
                                        mUsers.add(user);
                                    }
//                                }
//                            } else {
//                                mUsers.add(user);
//                            }
                        }
                    }

//    }
//                        if (user.getId().equals(chatlist.getId())){
//                            mUsers.add(user);
//                        }
//                    }
//                }
//                userAdapter.notifyDataSetChanged();
//                userAdapter.notifyDataSetChanged();
                userAdapter = new getuserinlistAdapter(t11_2_Messagelist_recentusers.this, mUsers,true);
                recyclerviewmessageslist222.setAdapter(userAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
