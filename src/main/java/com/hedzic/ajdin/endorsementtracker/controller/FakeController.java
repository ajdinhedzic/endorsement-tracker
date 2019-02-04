package com.hedzic.ajdin.endorsementtracker.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FakeController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello World!";
    }
}
