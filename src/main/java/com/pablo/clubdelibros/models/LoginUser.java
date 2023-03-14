package com.pablo.clubdelibros.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class LoginUser {
    @NotEmpty (message="Your email is required")
    @Email(message="Invalid email address")
    private String email;

    @NotEmpty (message="Please enter your password")
    private String password;

    public LoginUser() {}

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