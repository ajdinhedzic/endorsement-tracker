package com.hedzic.ajdin.endorsementtracker.service;

import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static com.sendgrid.Method.POST;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class EmailServiceTest {

    @InjectMocks
    private EmailService emailService;

    @Mock
    private SendGrid sendGrid;

    @Test
    public void passwordEmailResetEmailCallsSendWithCorrectUrl() throws Exception {
        ReflectionTestUtils.setField(emailService, "passwordResetTemplateId", "template-uuid" );
        emailService.sendPasswordResetEmailTo("any@any.com", "abcdef");
        ArgumentCaptor<Request> captor = ArgumentCaptor.forClass(Request.class);
        verify(sendGrid).api(captor.capture());
        assertEquals(POST, captor.getValue().getMethod());
        assertEquals("mail/send", captor.getValue().getEndpoint());
        assertTrue(captor.getValue().getBody().contains("any@any.com"));
        assertTrue(captor.getValue().getBody().contains("abcdef"));
        assertTrue(captor.getValue().getBody().contains("template-uuid"));
    }
}