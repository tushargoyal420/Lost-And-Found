package com.example.myapplication.t2models;

public class ModelMessagesList {
    private String Place;

//    private String Username;
//    public ModelMessagesList() {
//    }

//    public ModelMessagesList(String username) {
//        this.Username = username;
//    }
//    public String getUsername() {
//        return Username;
//    }
//
//    public void setUsername(String username) {
//        Username = username;
//    }

    public ModelMessagesList(){
    }
    public ModelMessagesList(String place) {
        this.Place = place;
    }

    public String getPlace(){
        return Place;
    }
    public void setPlace(String place) {
        Place = place;
    }

}