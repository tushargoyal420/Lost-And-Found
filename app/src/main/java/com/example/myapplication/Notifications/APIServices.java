package com.example.myapplication.Notifications;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIServices {
    @Headers(
            {
                    "Content-Type:application/json",
                    "Authorization:key=AAAAPDgmzN4:APA91bHYnsekNE9PhnMZI1Whg11Rf0T5nDlEqSGL5WVdzqns39sf-xO_-DRHyFQTcCEoilQEUvDnWDICoSmtjYG5Vp6pQDmvhhL3cnERnoufslohq0qUJsPih6ypo0OC5f2iPBC66Hzo"
            }
    )

    @POST("fcm/send")
    Call<MyResponse> sendNotification(@Body Sender body);
}