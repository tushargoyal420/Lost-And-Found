package com.example.myapplication.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

public class RetreiveUserDetails extends RecyclerView.ViewHolder {

    public ImageView mprofileimage;
    public TextView mprofileusername,mprofilephonenumber,mprofileemailaddress;

    public RetreiveUserDetails(@NonNull View itemView) {
        super(itemView);

        mprofileimage= itemView.findViewById(R.id.profileimage);
        mprofileusername= itemView.findViewById(R.id.profileusername);
        mprofilephonenumber= itemView.findViewById(R.id.profilephonenumber);
        mprofileemailaddress= itemView.findViewById(R.id.profileemailaddress);
    }
}
