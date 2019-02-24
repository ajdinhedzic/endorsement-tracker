package com.hedzic.ajdin.endorsementtracker.service;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.*;

public class EncryptionProviderTest {

    @Test
    public void encryptPasswordSaltsPassword() throws Exception {
        EncryptionProvider encryptionProvider = new EncryptionProvider();
        String password = "abc";
        String salt = BCrypt.gensalt();
        String encryptedPassword = encryptionProvider.encrypt(password, salt);
        assertEquals(BCrypt.hashpw(password, salt), encryptedPassword);
    }
}