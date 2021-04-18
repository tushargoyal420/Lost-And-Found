package com.example.myapplication;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;


public class t8_2_Upload_found_item extends AppCompatActivity {

    ImageButton mbackbutaddlost;
    EditText mnameoflostitem, mtypeoflostitem, mlostplace, mdescriptionoflostitem, mlostother;
    Button mlostsubmitbut,muploadfoundimagebut,mselectfoundimagebut;
    ImageView maddfoundimgepreview;

    FirebaseAuth fAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("lostitems");
    private StorageReference reference=  FirebaseStorage.getInstance().getReference();

    private Uri imageUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t82_activity_upload_found_item);

        mnameoflostitem=findViewById(R.id.nameoflostitem);
        mtypeoflostitem=findViewById(R.id.typeoflostitem);
        mlostplace=findViewById(R.id.lostplace);
        mdescriptionoflostitem=findViewById(R.id.descriptionoflostitem);
        mlostother=findViewById(R.id.lostother);

        //Image
        maddfoundimgepreview =findViewById(R.id.addfoundimgpreview);
        muploadfoundimagebut =findViewById(R.id.uploadfoundimagebut);
        //mselectfoundimagebut =findViewById(R.id.selectfoundimagebut);

        fAuth = FirebaseAuth.getInstance();     //for take instance from the our firebase

        mlostsubmitbut=findViewById(R.id.lostsubmitbut);
        mlostsubmitbut.setOnClickListener(this::onClick);

        mbackbutaddlost = findViewById(R.id.backbutaddlost);
        mbackbutaddlost.setOnClickListener(this::onClick);

        muploadfoundimagebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });
//        muploadfoundimagebut.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(imageUri != null){
//                    uploadimgtoFirebase(imageUri);
//                }else{
//                    Toast.makeText(t8_2_Upload_found_item.this, "Please select image", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            maddfoundimgepreview.setImageURI(imageUri);
        }
    }
    private void uploadimgtoFirebase(Uri uri){

        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Model model = new Model(uri.toString());
                        String modelId = root.push().getKey();
                        root.child(modelId).setValue(model);
                    }
                });
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {}
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(t8_2_Upload_found_item.this, "Uploading Failed!.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private  String getFileExtension(Uri mUri){
        ContentResolver cr= getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }
    private void uploadlostitem(){
        String nameoflostitem = mnameoflostitem.getText().toString();
        String typeoflostitem = mtypeoflostitem.getText().toString();
        String lostplace = mlostplace.getText().toString();
        String descriptionoflostitem = mdescriptionoflostitem.getText().toString();
        String lostother = mlostother.getText().toString();
//        String userMap1;
        if(imageUri == null){
//            Toast.makeText(t8_2_Upload_found_item.this, "Please select image", Toast.LENGTH_SHORT).show();
            muploadfoundimagebut.setError("Please Select image");
            return;
        }
        if (TextUtils.isEmpty(nameoflostitem)) {
            mnameoflostitem.setError("Please enter an name of item");
            return;
        }
        if (TextUtils.isEmpty(typeoflostitem)) {
            mtypeoflostitem.setError("Please enter an type of item");
            return;
        }
        if (TextUtils.isEmpty(lostplace)) {
            mlostplace.setError("Please enter place");
            return;
        }
        if (TextUtils.isEmpty(descriptionoflostitem)) {
            mdescriptionoflostitem.setError("Please enter description");
        }
        else {
            uploadimgtoFirebase(imageUri);

            HashMap<String, String> userMap = new HashMap<>();
            userMap.put("Name of item", nameoflostitem);
            userMap.put("Type of item", typeoflostitem);
            userMap.put("Place", lostplace);
            userMap.put("Description", descriptionoflostitem);
            userMap.put("Other", lostother);
//            userMap.put("imageurl", imageUri);
            root.push().setValue(userMap);
            Toast.makeText(t8_2_Upload_found_item.this, "Uploaded successfully", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(this, t8_2_Upload_found_item.class));
            finish();
        }
    }
//    public void uploaddata(HashMap<String, String> userMap){
//        root.push().setValue(userMap);
//    }

    public void onClick(View view) {
        if (view == mbackbutaddlost) {
            finish();
        }
        else if(view== mlostsubmitbut){
            uploadlostitem();
        }
    }
}