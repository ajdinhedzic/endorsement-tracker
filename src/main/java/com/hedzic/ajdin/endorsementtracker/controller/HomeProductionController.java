package com.hedzic.ajdin.endorsementtracker.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import static com.hedzic.ajdin.endorsementtracker.config.SpringProfile.PRODUCTION;

@Controller
@Profile({PRODUCTION})
public class HomeProductionController {

    @GetMapping("/private")
    public String redirectToRoot() {
        return "redirect:/";
    }
}
