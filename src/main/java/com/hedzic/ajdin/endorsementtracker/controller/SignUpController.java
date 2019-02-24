package com.hedzic.ajdin.endorsementtracker.controller;

import com.hedzic.ajdin.endorsementtracker.entity.UserAccount;
import com.hedzic.ajdin.endorsementtracker.domain.UserAuthentication;
import com.hedzic.ajdin.endorsementtracker.service.AppUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class SignUpController {

    @Autowired
    private AppUserDetailsService appUserDetailsService;

    @PostMapping("/api/signup")
    public ResponseEntity signup(@RequestBody UserAuthentication userAuthorization) {
        if (userAuthorization.getEmail().isEmpty() || userAuthorization.getPassword().isEmpty()) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        appUserDetailsService.signUp(new UserAccount(userAuthorization.getEmail(), userAuthorization.getPassword()));
        return new ResponseEntity(HttpStatus.CREATED);
    }

}