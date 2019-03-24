package com.hedzic.ajdin.endorsementtracker.service;

import com.hedzic.ajdin.endorsementtracker.entity.Pilot;
import com.hedzic.ajdin.endorsementtracker.entity.UserAccount;
import com.hedzic.ajdin.endorsementtracker.entity.UserPasswordReset;
import com.hedzic.ajdin.endorsementtracker.repository.PilotRepository;
import com.hedzic.ajdin.endorsementtracker.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PilotRepository pilotRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private DateProvider dateProvider;

    @Autowired
    private EmailService emailService;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        UserAccount user = userAccountRepository.findByEmail(emailAddress);
        if (user == null) {
            throw new UsernameNotFoundException(emailAddress);
        }
        return new User(emailAddress, user.getPassword(), Collections.emptyList());
    }

    public Pilot signUp(UserAccount userAccount) {
        Optional<UserAccount> user = Optional.ofNullable(userAccountRepository.findByEmail(userAccount.getEmail()));
        if (user.isPresent()) {
            return null;
        }
        String encryptedPassword = passwordEncoder.encode(userAccount.getPassword());
        UserAccount entity = new UserAccount(userAccount.getEmail(), encryptedPassword);
        UserAccount savedUserAccount = userAccountRepository.save(entity);
        return pilotRepository.save(new Pilot(savedUserAccount));
    }

    public void requestForgottenPasswordEmailFor(String email) {
        Optional<UserAccount> userAccount = Optional.ofNullable(userAccountRepository.findByEmail(email));
        if (userAccount.isPresent()) {
            UserPasswordReset passwordReset = new UserPasswordReset();
            String passwordResetToken = UUID.randomUUID().toString();
            passwordReset.setPasswordResetToken(passwordResetToken);
            passwordReset.setPasswordResetExpirationDate(dateProvider.getCurrentTime().plusMinutes(15));
            passwordReset.setUserAccount(userAccount.get());
            userAccount.get().setUserPasswordReset(passwordReset);
            userAccountRepository.save(userAccount.get());
            emailService.sendPasswordResetEmailTo(email, passwordResetToken);
        }
    }

    public void resetPasswordFor(UserAccount userAccount, String passwordResetToken) {
        Optional<UserAccount> user = Optional.ofNullable(userAccountRepository.findByEmail(userAccount.getEmail()));
        if (user.isPresent() && passwordResetTokenNotExpired(user) && passwordResetTokenMatches(passwordResetToken, user)) {
            String encryptedPassword = passwordEncoder.encode(userAccount.getPassword());
            user.get().setPassword(encryptedPassword);
            user.get().setUserPasswordReset(null);
            userAccountRepository.save(user.get());
        }
    }

    private boolean passwordResetTokenNotExpired(Optional<UserAccount> user) {
        return user.get().getUserPasswordReset().getPasswordResetExpirationDate().isAfter(dateProvider.getCurrentTime());
    }

    private boolean passwordResetTokenMatches(String passwordResetToken, Optional<UserAccount> user) {
        return user.get().getUserPasswordReset().getPasswordResetToken().equals(passwordResetToken);
    }
}