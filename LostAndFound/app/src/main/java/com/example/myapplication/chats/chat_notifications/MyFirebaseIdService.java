package com.example.myapplication.chats.chat_notifications;

import android.nfc.Tag;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;

//public class MyFirebaseIdService extends FirebaseInstanceIdService {
//
//    @Override
//    public void onTokenRefresh() {
//        super.onTokenRefresh();
//        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        String refreshToken = FirebaseInstanceId.getInstance().getToken();
//        if (firebaseUser != null){
//            updateToken(refreshToken);
//        }
//    }

public class MyFirebaseIdService extends FirebaseMessagingService {
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        String refreshToken = String.valueOf(FirebaseMessaging.getInstance().getToken());
        if (firebaseUser != null){
            updateToken(refreshToken);
        }
//        Log.e(,"Refreshed token" + refreshToken);
//        sendRegistrationToServer(refreshToken);
    }

    private void updateToken(String refreshToken) {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tokens");
        com.example.myapplication.chats.chat_notifications.Token token = new com.example.myapplication.chats.chat_notifications.Token(refreshToken);
        reference.child(firebaseUser.getUid()).setValue(token);
    }
}
