package com.hedzic.ajdin.endorsementtracker.controller;

import com.hedzic.ajdin.endorsementtracker.domain.UserAuthentication;
import com.hedzic.ajdin.endorsementtracker.entity.Pilot;
import com.hedzic.ajdin.endorsementtracker.entity.UserAccount;
import com.hedzic.ajdin.endorsementtracker.service.UserDetailsServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class SignUpControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SignUpController signUpController;

    @Mock
    private UserDetailsServiceImpl userDetailsService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(signUpController).build();
    }

    @Test
    public void signUpRouteReturnsCreated() throws Exception {
        when(userDetailsService.signUp(any(UserAccount.class))).thenReturn(new Pilot());
        mockMvc.perform(post("/api/signup")
                .contentType("application/json")
                .content("{\"email\":\"hello@hello.com\", \"password\":\"admin\"}"))
                .andExpect(status().isCreated());
    }


    @Test
    public void signUpReturns400WhenEmailEmptyString() throws Exception {
        mockMvc.perform(post("/api/signup")
                .contentType("application/json")
                .content("{\"email\":\"\", \"password\":\"password\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void signUpReturns400WhenPasswordEmptyString() throws Exception {
        mockMvc.perform(post("/api/signup")
                .contentType("application/json")
                .content("{\"email\":\"hello@hello.com\", \"password\":\"\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void signUpCallsAuthRepositoryWithUserCredentials() throws Exception {
        signUpController.signup(createUserAuthentication());
        ArgumentCaptor<UserAccount> userDetailsCaptor = ArgumentCaptor.forClass(UserAccount.class);
        verify(userDetailsService).signUp(userDetailsCaptor.capture());
        assertEquals("email@email", userDetailsCaptor.getValue().getEmail());
        assertEquals("pass", userDetailsCaptor.getValue().getPassword());
    }

    @Test
    public void signUpReturns409ConflictWhenServiceReturnsNull() throws Exception {
        ResponseEntity response = signUpController.signup(createUserAuthentication());
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());

    }

    @Test
    public void forgotPasswordReturns200() throws Exception {
        mockMvc.perform(post("/api/forgotPassword")
                .contentType("application/json")
                .content("{\"email\":\"hello@hello.com\"}"))
                .andExpect(status().isOk());
        verify(userDetailsService).requestForgottenPasswordEmailFor("hello@hello.com");
    }

    @Test
    public void resetPasswordReturns200() throws Exception {
        ArgumentCaptor<UserAccount> captor = ArgumentCaptor.forClass(UserAccount.class);
        mockMvc.perform(post("/api/resetPassword")
                .contentType("application/json")
                .content("{\"email\":\"hello@hello.com\", \"password\":\"admin\", \"token\":\"abcd1234\"}"))
                .andExpect(status().isOk());
        verify(userDetailsService).resetPasswordFor(captor.capture(), eq("abcd1234"));
    }

    @Test
    public void resetPasswordDoesNotCallUserDetailsServiceWhenEmailEmpty() throws Exception {
        mockMvc.perform(post("/api/resetPassword")
                .contentType("application/json")
                .content("{\"email\":\"\", \"password\":\"password\"}"))
                .andExpect(status().isBadRequest());
        verifyZeroInteractions(userDetailsService);
    }

    @Test
    public void resetPasswordDoesNotCallUserDetailsServiceWhenTokenEmpty() throws Exception {
        mockMvc.perform(post("/api/resetPassword")
                .contentType("application/json")
                .content("{\"email\":\"hello@hello.com\", \"password\":\"admin\", \"token\":\"\"}"))
                .andExpect(status().isBadRequest());
        verifyZeroInteractions(userDetailsService);
    }

    private UserAuthentication createUserAuthentication() {
        UserAuthentication userAuthorization = new UserAuthentication();
        userAuthorization.setEmail("email@email");
        userAuthorization.setPassword("pass");
        return userAuthorization;
    }
}