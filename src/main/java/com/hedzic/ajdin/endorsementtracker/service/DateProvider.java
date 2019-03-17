package com.hedzic.ajdin.endorsementtracker.service;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DateProvider {

    public LocalDateTime getCurrentTime() {
        return LocalDateTime.now();
    }
}
