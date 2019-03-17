package com.hedzic.ajdin.endorsementtracker.service;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static com.sendgrid.Method.POST;

@Component
public class EmailService {

    @Autowired
    private SendGrid sendGrid;

    @Value("${application.sendgrid.password-reset-template-id}")
    private String passwordResetTemplateId;

    public void sendPasswordResetEmailTo(String email, String token) {
        try {
            Request request = new Request();
            request.setMethod(POST);
            request.setEndpoint("mail/send");
            request.setBody(email + token);
            Content content = new Content("text/plain", token);
            Mail mail = new Mail(new Email("ahedzic@gmail.com"), "Password Reset", new Email(email), content);
            mail.setTemplateId(passwordResetTemplateId);
            request.setBody(mail.build());
            sendGrid.api(request);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
