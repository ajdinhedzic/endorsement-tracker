package com.hedzic.ajdin.endorsementtracker.config;

import com.hedzic.ajdin.endorsementtracker.repository.FakeSendGrid;
import com.sendgrid.SendGrid;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import static com.hedzic.ajdin.endorsementtracker.config.SpringProfile.TESTING;

@org.springframework.boot.test.context.TestConfiguration
public class TestConfiguration {

    @Bean
    @Profile({TESTING})
    public SendGrid sendGridTesting() {
        return new FakeSendGrid("apiKey");
    }

}
