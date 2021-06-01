package com.example.myapplication.t2models;

public class userlist {
    private String Email;
    private String Name;
    private String Id;
    private String Phonenumber;

    public userlist(String email, String name, String id, String phonenumber, String search) {
        this.Email = id;
        this.Name = name;
        this.Id = id;
        this.Phonenumber = phonenumber;
    }

    public userlist() {

    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }
}
