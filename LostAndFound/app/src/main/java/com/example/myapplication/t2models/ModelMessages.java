package com.example.myapplication.t2models;

public class ModelMessages {
    private String Message;

    public ModelMessages(){ }

    public String getMessage() { return Message; }

    public void setMessage(String message) { Message = message; }
    public ModelMessages(String message) { this.Message = message; }
}
