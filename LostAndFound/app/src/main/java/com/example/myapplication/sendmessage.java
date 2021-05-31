package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.myapplication.t1adapters.MyAdapterShowMessages;
import com.example.myapplication.t1adapters.MyAdapterShowMessagesList;
import com.example.myapplication.t2models.Model;
import com.example.myapplication.t2models.ModelMessages;
import com.example.myapplication.t2models.ModelMessagesList;
import com.example.myapplication.t2models.ModelSendMessage;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class sendmessage extends AppCompatActivity {

    ImageButton mbackbutmessage; Button mmessagesendbut; EditText mtypemessage; FirebaseAuth fAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root=db.getReference().child("usermessages");
    private DatabaseReference root2;
    private DatabaseReference root3;
    @TargetApi(Build.VERSION_CODES.O)
    private int number;
    private static final AtomicInteger count = new AtomicInteger(0);

    ModelSendMessage modelSendMessage;
    private String User;
//    private String User22;

    //for retreive
    private RecyclerView mrecyclerviewmessages;
    private MyAdapterShowMessages adapterShowMessages;
    private ArrayList<ModelMessages> list3;
    private DatabaseReference retroot;
    private DatabaseReference retroot2;

    ListView mlistview;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendmessage);
        mtypemessage = findViewById(R.id.typemessage);
        mbackbutmessage = findViewById(R.id.backbutmessage);
        mmessagesendbut = findViewById(R.id.messagesendbut);

        modelSendMessage = new ModelSendMessage();
        fAuth = FirebaseAuth.getInstance();

        User = fAuth.getCurrentUser().getUid();   //current user
        String User22 = getIntent().getStringExtra("User22");   //second user
        verify(User,User22);

        mmessagesendbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendmessage(User, User22);
            }
        });
        mbackbutmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void verify(String User, String User22){
        retroot= root.child(String.valueOf(User));
        retroot2= retroot.child(User22);

        mlistview=  findViewById(R.id.listview);
        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.listitem, list);
        mlistview.setAdapter(adapter);
        retroot2.addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot)
            {
                list.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren())
                {
                    list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void sendmessage(String User, String User22)
    {
        String typemessage = mtypemessage.getText().toString();
        if(TextUtils.isEmpty(typemessage)){
            mtypemessage.setError("Enter Message");
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd 'at' HH-mm-ss-SSS ");
            String currentDateandTime = sdf.format(new Date());

            root2=  root.child(String.valueOf(User));

            if(User.equals(User22)){
                root3=  root2.child(User22);
              root3.child(currentDateandTime).setValue(typemessage);
                mtypemessage.getText().clear();
            }else{
                root2.push().setValue(modelSendMessage);
            }
        }
    }
}

//            textView.setText(currentDateandTime);
//            modelSendMessage.setSend(typemessage);
//            HashMap<String, String> userMap= new HashMap<>();
//            userMap.put("send", typemessage);
//            userMap.put("time",currentDateandTime);
//                root3.child(String.valueOf(number)).setValue(userMap);
//                number = count.incrementAndGet();
//            root.child(String.valueOf(user));
//          root.push().setValue(String.valueOf(user));




//   for retreive
//        mrecyclerviewmessages = findViewById(R.id.recyclerviewmessages);
//        mrecyclerviewmessages.setLayoutManager(new LinearLayoutManager(this));
//        list3 = new ArrayList<>();
//        adapterShowMessages = new MyAdapterShowMessages(this, list3);
//        mrecyclerviewmessages.setAdapter(adapterShowMessages);





//        retroot2.addValueEventListener(new ValueEventListener() {
//            @Override
////            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
////                Map<String, Object> td = (HashMap<String,Object>) dataSnapshot.getValue();
////                List<String> values = (List<String>) td.values();
////                data((Map<String,Object>) dataSnapshot.getValue());
//
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
////                    ModelMessages model3= (ModelMessages) dataSnapshot.getValue();
////                    list3.add(model3);
//
//                }
//                adapterShowMessages.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//            }
//        });
