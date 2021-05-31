package com.example.myapplication;

public class Posts {
    private String date, description, imageUri,name_of_Item, place;

    public Posts() {
    }

    public Posts(String date, String description, String imageUri, String name_of_Item, String place) {
        this.date = date;
        this.description = description;
        this.imageUri = imageUri;
        this.name_of_Item = name_of_Item;
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getName_of_Item() {
        return name_of_Item;
    }

    public void setName_of_Item(String name_of_Item) {
        this.name_of_Item = name_of_Item;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
