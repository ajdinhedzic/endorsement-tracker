package com.hedzic.ajdin.endorsementtracker.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static com.hedzic.ajdin.endorsementtracker.config.SpringProfile.DEVELOPMENT;
import static com.hedzic.ajdin.endorsementtracker.config.SpringProfile.PRODUCTION;

@Configuration
public class SpringConfiguration {

    @Value("${application.sendgrid.key}")
    private String sendGridApiKey;

    @Bean
    @Profile({PRODUCTION, DEVELOPMENT})
    public SendGrid sendGrid() {
        return new SendGrid(sendGridApiKey);
    }

}
