package com.hedzic.ajdin.endorsementtracker.domain;

import org.springframework.lang.Nullable;

public class UserAuthentication {
    private String email;
    private String password;
    @Nullable
    private String token;

    public UserAuthentication() {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}