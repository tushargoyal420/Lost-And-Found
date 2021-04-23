package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

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

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class t8_2_upload_found_item extends AppCompatActivity {
    ImageButton mbackbutfound;
    EditText mnameofitem, mplace, mdescriptionofitem, mdate;
    Button mfoundsubmitbut, muploadfoundimagebut;
    ImageView maddfoundimgepreview;

    FirebaseAuth fAuth;
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference root = db.getReference().child("founditems");
    private StorageReference reference=  FirebaseStorage.getInstance().getReference();
    private Uri imageUri;
    Model model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.t7_2_activity_upload_found_item);
        mnameofitem=findViewById(R.id.nameofitem);
        mplace=findViewById(R.id.place);
        mdescriptionofitem=findViewById(R.id.descriptionofitem);
        mdate=findViewById(R.id.date);

        //Image
        maddfoundimgepreview =findViewById(R.id.addfoundimgpreview);
        muploadfoundimagebut =findViewById(R.id.uploadfoundimagebut);

        model = new Model();

        fAuth = FirebaseAuth.getInstance();     //for take instance from the our firebase

        mfoundsubmitbut=findViewById(R.id.foundsubmitbut);
        mfoundsubmitbut.setOnClickListener(this::onClick);

        mbackbutfound = findViewById(R.id.backbutfound);
        mbackbutfound.setOnClickListener(this::onClick);

        muploadfoundimagebut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            imageUri = data.getData();
            maddfoundimgepreview.setImageURI(imageUri);
        }
    }

    public void uploadimgtoFirebase(Uri uri){

        StorageReference fileRef = reference.child(System.currentTimeMillis() + "." + getFileExtension(uri));
        fileRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
//                        Model model = new Model(uri.toString());
                        model.setImageUri(uri.toString());

                        String nameoflostitem = mnameofitem.getText().toString();
                        String lostplace = mplace.getText().toString();
                        String descriptionoflostitem = mdescriptionofitem.getText().toString();
                        String date = mdate.getText().toString();
                        if (TextUtils.isEmpty(nameoflostitem)) {
                            mnameofitem.setError("Please enter an name of item");
                            return;
                        }
                        if (TextUtils.isEmpty(lostplace)) {
                            mplace.setError("Please enter place");
                            return;
                        }
                        if (TextUtils.isEmpty(descriptionoflostitem)) {
                            mdescriptionofitem.setError("Please enter description");
                        }
                        if (TextUtils.isEmpty(date)) {
                            mdate.setError("Please enter Date");
                        }

                        else{
                            model.setName_of_Item(nameoflostitem);
                            model.setPlace(lostplace);
                            model.setDescription(descriptionoflostitem);
                            model.setDate(date);
                            root.push().setValue(model);

                            Toast.makeText(t8_2_upload_found_item.this, "Congratualtions !! Uploaded successfully", Toast.LENGTH_SHORT).show();
                            finish();
                        }
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
                        Toast.makeText(t8_2_upload_found_item.this, "Uploading Failed!.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private  String getFileExtension(Uri mUri){
        ContentResolver cr= getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

    private void uploadfounditemimage(){
        if(imageUri == null){
            Toast.makeText(t8_2_upload_found_item.this, "Please select image", Toast.LENGTH_SHORT).show();
            return;
        }else {
            uploadimgtoFirebase(imageUri);
        }
    }

    public void onClick(View view) {
        if (view == mbackbutfound) {
            finish();
        }else if(view== mfoundsubmitbut){
            uploadfounditemimage();
        }
    }
}
