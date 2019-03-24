package com.hedzic.ajdin.endorsementtracker.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class UserAccount {

    @Id
    @GeneratedValue
    private long id;
    private String email;
    private String password;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private UserPasswordReset userPasswordReset;

    protected UserAccount() {
    }

    public UserAccount(String email, String password) {
        this.email = email;
        this.password = password;
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

    public void setPassword(String password) {
        this.password = password;
    }

    public UserPasswordReset getUserPasswordReset() {
        return userPasswordReset;
    }

    public void setUserPasswordReset(UserPasswordReset userPasswordReset) {
        this.userPasswordReset = userPasswordReset;
    }
}