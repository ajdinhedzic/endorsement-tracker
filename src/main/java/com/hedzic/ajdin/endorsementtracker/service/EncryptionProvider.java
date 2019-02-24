package com.hedzic.ajdin.endorsementtracker.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptionProvider {

    public String encrypt(String password, String salt) {
        return BCrypt.hashpw(password, salt);
    }
}