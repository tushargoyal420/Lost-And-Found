package com.example.myapplication.t2models;

public class Model {

    private String imageUri;
    private String Name_of_Item;
    private String Description;
    private String Place;
    private String Date;
    private String User;
//    private String Phone;

    public Model(){
    }
//    public Model(String imageUri, String name_of_Item, String place, String date){
    public Model(String imageUri, String name_of_Item, String description, String place, String date, String user){
        this.Place = place;
        this.imageUri = imageUri;
        this.Name_of_Item = name_of_Item;
        this.Description = description;
        this.Date = date;
        this.User = user;

//        this.Phone = phone;
    }

    public String getImageUri() {
        return imageUri;
    }
    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName_of_Item() {
        return Name_of_Item;
    }
    public void setName_of_Item(String name_of_Item) {
        Name_of_Item = name_of_Item;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }

    public String getPlace() {
        return Place;
    }
    public void setPlace(String place) {
        Place = place;
    }

    public String getDate() {
        return Date;
    }
    public void setDate(String date) {
        Date = date;
    }

    public String getUser() {
        return User;
    }

    public void setUser(String user) {
        User = user;
    }
//    public String getPhone() {
//        return Phone;
//    }
//    public void setPhone(String phone) {
//        Phone = phone;
//    }
}
