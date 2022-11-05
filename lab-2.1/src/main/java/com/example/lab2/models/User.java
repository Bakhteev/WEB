package com.example.lab2.models;

public class User {

    Integer id;
    String email;
    String passwordHash;

   public User(String email, String passwordHash){
       this.email = email;
       this.passwordHash = passwordHash;
   }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
