package com.hedzic.ajdin.endorsementtracker.service;

import com.hedzic.ajdin.endorsementtracker.entity.Pilot;
import com.hedzic.ajdin.endorsementtracker.entity.UserAccount;
import com.hedzic.ajdin.endorsementtracker.repository.PilotRepository;
import com.hedzic.ajdin.endorsementtracker.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PilotRepository pilotRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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
        if (!user.isPresent()) {
            String passwordSalt = BCrypt.gensalt();
            String encryptedPassword = passwordEncoder.encode(userAccount.getPassword());
            UserAccount entity = new UserAccount(userAccount.getEmail(), encryptedPassword, passwordSalt);
            UserAccount savedUserAccount = userAccountRepository.save(entity);
            return pilotRepository.save(new Pilot(savedUserAccount));
        }
        return null;
    }
}