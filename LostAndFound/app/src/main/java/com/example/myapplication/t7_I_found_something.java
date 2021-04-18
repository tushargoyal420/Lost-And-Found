package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GetTokenResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class t7_I_found_something extends AppCompatActivity {
    ImageButton mbackbutfound, mfoundaddimage;
    EditText mnameofitem, mtypeofitem, mplace, mdescriptionofitem, mother;
    Button mfoundsubmitbut;
//    String user;

    FirebaseAuth fAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("founditems");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t7_activity_i_found_something);

        mnameofitem=findViewById(R.id.nameofitem);
        mtypeofitem=findViewById(R.id.typeofitem);
        mplace=findViewById(R.id.place);
        mdescriptionofitem=findViewById(R.id.descriptionofitem);
        mother=findViewById(R.id.other);
        mfoundaddimage =findViewById(R.id.foundaddimage);

        fAuth = FirebaseAuth.getInstance();     //for take instance from the our firebase

        mfoundsubmitbut=findViewById(R.id.foundsubmitbut);
        mfoundsubmitbut.setOnClickListener(this::onClick);

        mbackbutfound = findViewById(R.id.backbutfound);
        mbackbutfound.setOnClickListener(this::onClick);

    }
    private void uploadfounditem(){
        String nameofitem = mnameofitem.getText().toString();
        String typeofitem = mtypeofitem.getText().toString();
        String place = mplace.getText().toString();
        String descriptionofitem = mdescriptionofitem.getText().toString();
        String other = mother.getText().toString();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        Task<GetTokenResult> user = cuser.getIdToken(true);

        if (TextUtils.isEmpty(nameofitem)) {
            mnameofitem.setError("Please enter an name of item");
            return;
        }
        if (TextUtils.isEmpty(typeofitem)) {
            mtypeofitem.setError("Please enter an type of item");
            return;
        }
        if (TextUtils.isEmpty(place)) {
            mplace.setError("Please enter place");
            return;
        }
        if (TextUtils.isEmpty(descriptionofitem)) {
            mdescriptionofitem.setError("Please enter description");
            return;
        }else {

            HashMap<String, String> userMap = new HashMap<>();

            userMap.put("Name of item", nameofitem);
            userMap.put("Type of item", typeofitem);
            userMap.put("Place", place);
            userMap.put("Description", descriptionofitem);
            userMap.put("Other", other);
            root.push().setValue(userMap);

//            HashMap<String, FirebaseUser> userMap2 = new HashMap<>();
//            userMap2.put("Name of the user",cuser);
//            root.push().setValue(userMap2);
            Toast.makeText(t7_I_found_something.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, t7_I_found_something.class));
        }
    }



    public void onClick(View view) {
        if (view == mbackbutfound) {
            finish();
        }
        else if(view== mfoundsubmitbut){
            uploadfounditem();
//            Snackbar.make(view, "Data submitted successfully", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
        }
    }

}