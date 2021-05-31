package com.example.myapplication.chats.Fragments;

import com.example.myapplication.chats.chat_notifications.MyResponse;
import com.example.myapplication.chats.chat_notifications.Sender;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAPDgmzN4:APA91bHYnsekNE9PhnMZI1Whg11Rf0T5nDlEqSGL5WVdzqns39sf-xO_-DRHyFQTcCEoilQEUvDnWDICoSmtjYG5Vp6pQDmvhhL3cnERnoufslohq0qUJsPih6ypo0OC5f2iPBC66Hzo"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}
