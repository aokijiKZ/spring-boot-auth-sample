package com.kuzan.temp.models;

public class UserModel {
    private String id;
    private String name;
    private String email;

    public UserModel() {
    }
    
    public UserModel(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public UserModel(String name, String email){
        this.name =name;
        this.email = email;
        this.id = String.valueOf(System.currentTimeMillis()); 
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
