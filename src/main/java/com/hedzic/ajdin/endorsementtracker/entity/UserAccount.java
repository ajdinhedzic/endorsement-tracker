package com.hedzic.ajdin.endorsementtracker.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String password;
    private String salt;

    protected UserAccount() {
    }

    public UserAccount(String email, String password, String salt) {
        this.email = email;
        this.password = password;
        this.salt = salt;
    }

    public UserAccount(String email, String password) {
        this(email, password, "");
    }

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }
}