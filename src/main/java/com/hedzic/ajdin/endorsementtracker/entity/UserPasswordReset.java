package com.hedzic.ajdin.endorsementtracker.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UserPasswordReset {

    @Id
    @GeneratedValue
    private long id;
    private String passwordResetToken;
    private LocalDateTime passwordResetExpirationDate;

    public String getPasswordResetToken() {
        return passwordResetToken;
    }

    public void setPasswordResetToken(String passwordResetToken) {
        this.passwordResetToken = passwordResetToken;
    }

    public LocalDateTime getPasswordResetExpirationDate() {
        return passwordResetExpirationDate;
    }

    public void setPasswordResetExpirationDate(LocalDateTime passwordResetExpirationDate) {
        this.passwordResetExpirationDate = passwordResetExpirationDate;
    }
}