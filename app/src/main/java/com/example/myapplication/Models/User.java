package com.example.myapplication.Models;

public class User {

    private String id;
    private String Name;
    private String Email;
    private String Phonenumber;
    private String imageURL;
    private String status;
    private String search;


    public User(String email, String phonenumber) {

    }

    public User(String id, String name, String imageURL, String status, String search,String email, String phonenumber) {
        this.id = id;
        this.Name = name;
        this.imageURL = imageURL;
        this.status = status;
        this.search = search;
        this.Email = email;
        this.Phonenumber = phonenumber;
    }

    public User() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }
}