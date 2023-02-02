package com.vertie.javacode.models;

import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {

    @SerializedName("EmailorVIN")
    public String email;

    @SerializedName("Password")
    public String password;

    public LoginRequestModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
