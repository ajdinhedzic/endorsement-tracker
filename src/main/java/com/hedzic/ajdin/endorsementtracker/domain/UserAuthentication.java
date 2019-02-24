package com.hedzic.ajdin.endorsementtracker.domain;

public class UserAuthentication {
    private String email;
    private String password;

    public UserAuthentication() {
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}