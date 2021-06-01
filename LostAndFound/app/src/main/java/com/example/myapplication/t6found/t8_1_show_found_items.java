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
import com.example.myapplication.t2models.Model;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class t8_1_show_found_items extends AppCompatActivity {

    ImageButton mbacktouploadfoundbutton, maddfounditembut;
    ListView mlistvieww;
    EditText minputSearch;
    TextView minputSearch2;
    private RecyclerView mrecyclerViewfound;
//    private FirebaseDatabase db= FirebaseDatabase.getInstance();
 //    private DatabaseReference root=db.getReference().child("founditems");
//    private MyAdapter adapter;
//    private ArrayList<Model> list;
    FirebaseRecyclerAdapter<Posts, MyViewHolder2> adapter1;
    FirebaseRecyclerOptions<Posts> options;
//    DatabaseReference Dataref;
    DatabaseReference PostRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t8_1_show_found_items);

        mbacktouploadfoundbutton = findViewById(R.id.backtouploadfound);
        maddfounditembut = findViewById(R.id.addfounditembut);

//        mlistvieww = findViewById(R.id.listvieww);
//        mrecyclerViewfound.setHasFixedSize(true);
//        Dataref= FirebaseDatabase.getInstance().getReference().child("founditems");
        mrecyclerViewfound = findViewById(R.id.recyclerviewfound);
        mrecyclerViewfound.setLayoutManager(new LinearLayoutManager(this));

        PostRef = FirebaseDatabase.getInstance().getReference().child("founditems");
        minputSearch=findViewById(R.id.inputSearch);

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
        mbacktouploadfoundbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        maddfounditembut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(t8_1_show_found_items.this, t8_2_upload_found_item.class));
            }
        });
        LoadPost();
    }

    private void LoadPost() {
        options= new FirebaseRecyclerOptions.Builder<Posts>().setQuery(PostRef, Posts.class).build();
        adapter1 = new FirebaseRecyclerAdapter<Posts, MyViewHolder2>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder2 holder, int position, @NonNull Posts model) {
                holder.itemname.setText(model.getName_of_Item());
                holder.itemplace.setText(model.getPlace());
                holder.itemdate.setText(model.getDate());
//                holder.itemdes.setText(model.getDescription());
                Picasso.get().load(model.getImageUri()).into(holder.itemimage);

                holder.card.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent=new Intent(t8_1_show_found_items.this, itemmoredetails.class);
                            intent.putExtra("ItemKey",getRef(position).getKey());
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
//    private void LoadData(String data) {
//
//                    Query query=Dataref2.startAt(data).endAt(data+"\uf8ff");
//                    options=new FirebaseRecyclerOptions.Builder<Model>().setQuery(query,Model.class).build();
//                    adapter1=new FirebaseRecyclerAdapter<Model, MyViewHolder>(options) {
//                @Override
//                protected void onBindViewHolder(@NonNull MyViewHolder holder, final int position, @NonNull Model model) {
//                    holder.namee.setText(model.getName_of_Item());
//                    holder.datee.setText(model.getDate());
//                    holder.placee.setText(model.getPlace());
//                    Picasso.get().load(model.getImageUri()).into(holder.imagev);
////                    String a = model.getName_of_Item();
////                    minputSearch2.setText(a);
//    //                mminputSearch.setText(model.getName_of_Item());
//
//                    holder.card.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent=new Intent(t8_1_show_found_items.this, itemmoredetails.class);
//                            intent.putExtra("ItemKey",getRef(position).getKey());
//                            startActivity(intent);
//                        }
//                });
//
//            }
//            @NonNull @Override
//            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//                View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview,parent,false);
//                return new MyViewHolder(v);
//            }
//        };
//        adapter1.startListening();
//        mrecyclerViewfound.setAdapter(adapter1);
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder{
//
//         TextView namee;
//         TextView datee;
//         TextView placee;
//         ImageView imagev;
//        //    public Button contactmessagebut;
//         View vi;
//         CardView card;
//         MyViewHolder(@NonNull View cardView){
//            super(cardView);
//            namee = cardView.findViewById(R.id.nameofitem);
//            datee = cardView.findViewById(R.id.dateofitem);
//            placee = cardView.findViewById(R.id.placeofitem);
//            imagev = cardView.findViewById(R.id.imageofitem);
//            vi= cardView;
//            card = cardView.findViewById(R.id.card);
//        }





//    public void onClick(View view) {
//        if (view == mbacktouploadfoundbutton) {
//            finish();
//        }else if (view == maddfounditembut){
//            finish();
//            startActivity(new Intent(this, t8_2_upload_found_item.class));
//        }
//    }
}