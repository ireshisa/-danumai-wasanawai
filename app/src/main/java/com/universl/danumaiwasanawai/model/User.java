package com.universl.danumaiwasanawai.model;

public class User {


    private String user_id;
    private String user_name;
    private String 	email;
    private String 	image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public User(String user_id, String user_name, String email,String image) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.email = email;
        this.image = image;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
