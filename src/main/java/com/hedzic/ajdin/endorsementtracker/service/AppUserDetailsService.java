package com.hedzic.ajdin.endorsementtracker.service;

import com.hedzic.ajdin.endorsementtracker.entity.UserAccount;
import com.hedzic.ajdin.endorsementtracker.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private EncryptionProvider encryptionProvider;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        String securityCode = "";
        if (securityCode == null) {
            throw new UsernameNotFoundException(emailAddress);
        }
        return new User(emailAddress, securityCode, Collections.emptyList());
    }

    public void signUp(UserAccount userAccount) {
        Optional<UserAccount> user = Optional.ofNullable(userAccountRepository.findByEmail(userAccount.getEmail()));
        if (!user.isPresent()) {
            String passwordSalt = BCrypt.gensalt();
            String encryptedPassword = encryptionProvider.encrypt(userAccount.getPassword(), passwordSalt);
            userAccountRepository.save(new UserAccount(userAccount.getEmail(), encryptedPassword, passwordSalt));
        }
    }
}