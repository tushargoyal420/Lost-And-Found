package com.example.myapplication;

public class retreive {

    private String imageUri;
    private String Name_of_Item;
    private String Description;
    private String Place;
    private String Other;
//    private String Email;
//    private String Name;

    public retreive() {
    }

    public retreive(String imageUri, String name_of_Item, String description, String place, String other) {
        this.imageUri = imageUri;
        Name_of_Item = name_of_Item;
        Description = description;
        Place = place;
        Other = other;
//        Name = name;
//        Email =email;
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

    public String getOther() {
        return Other;
    }

    public void setOther(String other) {
        Other = other;
    }

//    public String getEmail() {
//        return Email;
//    }
//
//    public void setEmail(String email) {
//        Email = email;
//    }
//
//    public String getName() {
//        return Name;
//    }
//
//    public void setName(String name) {
//        Name = name;
//    }
}
