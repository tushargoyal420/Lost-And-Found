package com.example.myapplication.t6found;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.myapplication.MyViewHolder2;
import com.example.myapplication.Posts;
import com.example.myapplication.R;
import com.example.myapplication.itemmoredetails;
import com.example.myapplication.t10_2_MessageList;
import com.example.myapplication.t1adapters.getuserinlistAdapter;
import com.example.myapplication.t1adapters.searchitem;
import com.example.myapplication.t2models.Model;
import com.example.myapplication.t2models.userlist;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

public class t8_1_show_found_items extends AppCompatActivity {
//    private String fAuth = FirebaseDatabase;
//    private searchitem userAdapter;
    private List<Model> mUsers;
    EditText minputSearchfounditem;

    private ImageButton mbacktodashfromfound, maddfounditembut;
    private EditText minputSearchFound;
    private RecyclerView mrecyclerViewfound;
    private FirebaseRecyclerAdapter<Posts, MyViewHolder2> adapter1;
    private FirebaseRecyclerOptions<Posts> options;
    private  DatabaseReference PostRef= FirebaseDatabase.getInstance().getReference().child("founditems");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t8_1_show_found_items);
//Buttons
        mbacktodashfromfound = findViewById(R.id.backtodashfromfound);
        mbacktodashfromfound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        maddfounditembut = findViewById(R.id.addfounditembut);
        maddfounditembut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(t8_1_show_found_items.this, t8_2_upload_found_item.class));
            }
        });

        minputSearchfounditem =findViewById(R.id.inputSearchfounditem);
//        minputSearchfounditem.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                searchUsers(charSequence.toString().toLowerCase());
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        mrecyclerViewfound = findViewById(R.id.recyclerviewfound);
        mrecyclerViewfound.setLayoutManager(new LinearLayoutManager(this));

        minputSearchFound=findViewById(R.id.minputSearchFound);
//Search item
        //        minputSearch2=findViewById(R.id.inputSearch2);
//        LoadData("");
//        minputSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) { }
//            @Override
//            public void afterTextChanged(Editable s) {
//                if (s.toString()!=null)
//                {
//                    LoadData(s.toString());
//                }
//                else {
//                    LoadData("");
//                }
//            }});

        LoadPost();
    }

    private void searchUsers(String s) {
        final FirebaseUser fuser = FirebaseAuth.getInstance().getCurrentUser();
        Query query = FirebaseDatabase.getInstance().getReference("founditems").orderByChild("name_of_item").startAt(s).endAt(s+"\uf8ff");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@io.reactivex.annotations.NonNull DataSnapshot dataSnapshot) {
                mUsers.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    Model user = snapshot.getValue(Model.class);

                    assert user != null;
                    assert fuser != null;
                    if (!user.getName_of_Item().equals(fuser.getUid())){
                        mUsers.add(user);
                    }
                }
                adapter1.startListening();
                mrecyclerViewfound.setAdapter(adapter1);
            }

            @Override
            public void onCancelled(@io.reactivex.annotations.NonNull DatabaseError databaseError) {


    }
        });



    }
    private void LoadPost(){
        options= new FirebaseRecyclerOptions.Builder<Posts>().setQuery(PostRef, Posts.class).build();
        adapter1 = new FirebaseRecyclerAdapter<Posts, MyViewHolder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder2 holder, int position, @NonNull Posts model) {
                holder.itemname.setText(model.getName_of_Item());
                holder.itemplace.setText(model.getPlace());
                holder.itemdate.setText(model.getDate());
                Picasso.get().load(model.getImageUri()).into(holder.itemimage);

                holder.card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String found= "found";
                            Intent intent=new Intent(t8_1_show_found_items.this, itemmoredetails.class);
                            intent.putExtra("ItemKey",getRef(position).getKey());
                            intent.putExtra("Item",found);
                            startActivity(intent);
                        }
                });
            }
            @NonNull
            @Override
            public MyViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singleview,parent, false);
                return new MyViewHolder2(view);
            }
        };
        adapter1.startListening();
        mrecyclerViewfound.setAdapter(adapter1);
    }
}