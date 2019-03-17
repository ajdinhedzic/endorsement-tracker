package com.hedzic.ajdin.endorsementtracker.controller;

import com.hedzic.ajdin.endorsementtracker.domain.UserAuthentication;
import com.hedzic.ajdin.endorsementtracker.domain.UserEmail;
import com.hedzic.ajdin.endorsementtracker.entity.Pilot;
import com.hedzic.ajdin.endorsementtracker.entity.UserAccount;
import com.hedzic.ajdin.endorsementtracker.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static org.springframework.http.HttpStatus.*;

@Controller
public class SignUpController {

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping("/api/signup")
    public ResponseEntity signup(@RequestBody UserAuthentication userAuthorization) {
        if (userAuthorization.getEmail().isEmpty() || userAuthorization.getPassword().isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Pilot pilot = userDetailsServiceImpl.signUp(new UserAccount(userAuthorization.getEmail(), userAuthorization.getPassword()));
        return pilot == null ? new ResponseEntity(CONFLICT) : new ResponseEntity(CREATED);
    }

    @PostMapping("/api/forgotPassword")
    public ResponseEntity forgotPassword(@RequestBody UserEmail email) {
        userDetailsServiceImpl.requestForgottenPasswordEmailFor(email.getEmail());
        return new ResponseEntity(OK);
    }
}