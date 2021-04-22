package com.example.myapplication;

public class Model {

    private String imageUri;
    private String Name_of_Item;
    private String Description;
    private String Place;
    private String Date;

    public Model(){
    }

    public Model(String imageUri, String name_of_Item, String description, String place, String date){
        this.imageUri = imageUri;
        this.Name_of_Item = name_of_Item;
        this.Description = description;
        this.Place = place;
        this.Date = date;

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
}
