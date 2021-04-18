////package com.example.myapplication;
////
////import com.google.firebase.auth.FirebaseAuth;
////import com.google.firebase.auth.FirebaseUser;
////import com.google.firebase.database.DatabaseReference;
////import com.google.firebase.database.FirebaseDatabase;
////
////public class getusername {
////    FirebaseAuth fAuth;
////    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
////    private FirebaseDatabase db = FirebaseDatabase.getInstance();
////    private DatabaseReference root = db.getReference().child("users");
////}
//
//btnSelect.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v)
//        {
//        SelectImage();
//        }
//        });
//
//        // on pressing btnUpload uploadImage() is called
//        btnUpload.setOnClickListener(new View.OnClickListener() {
//@Override
//public void onClick(View v)
//        {
//        uploadImage();
//        }
//        });
//// UploadImage method
//
//private void uploadImage()
//        {
//        if(filePath!=null){
//        // Code for showing progressDialog while uploading
//        ProgressDialog progressDialog =new ProgressDialog(this);
//        progressDialog.setTitle("Uploading...");
//        progressDialog.show();
//
//        // Defining the child of storageReference
//        StorageReference ref =storageReference.child("images/" +UUID.randomUUID().toString());
//        // adding listeners on upload
//        // or failure of image
//        ref.putFile(filePath)
//        .addOnSuccessListener(
//        new OnSuccessListener<UploadTask.TaskSnapshot>(){
//
//@Override
//public void onSuccess(
//        UploadTask.TaskSnapshot taskSnapshot)
//        {
//            // Image uploaded successfully   // Dismiss dialog
//            progressDialog.dismiss();
//            Toast.makeText(MainActivity.this, "Image Uploaded!!", Toast.LENGTH_SHORT) .show();
//        }
//})
//
//        .addOnFailureListener(new OnFailureListener(){
//        @Override
//        public void onFailure(@NonNull Exception e){
//        // Error, Image not uploaded
//            progressDialog.dismiss();
//            Toast.makeText(MainActivity.this, "Failed "+e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        })
//        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>(){
//
//// Progress Listener for loading
//// percentage on the dialog box
//@Override
//public void onProgress(
//        UploadTask.TaskSnapshot taskSnapshot)
//        {
//        double progress=(100.0
//        *taskSnapshot.getBytesTransferred()
//        /taskSnapshot.getTotalByteCount());
//        progressDialog.setMessage( "Uploaded " +(int)progress+"%");
//        }
//        });
//    }
//}