package com.hedzic.ajdin.endorsementtracker.controller;

import com.hedzic.ajdin.endorsementtracker.entity.UserAccount;
import com.hedzic.ajdin.endorsementtracker.domain.UserAuthentication;
import com.hedzic.ajdin.endorsementtracker.service.AppUserDetailsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
public class SignUpControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private SignUpController signUpController;

    @Mock
    private AppUserDetailsService userDetailsService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(signUpController).build();
    }

    @Test
    public void signUpRouteReturnsCreated() throws Exception {
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
        UserAuthentication userAuthorization = new UserAuthentication();
        userAuthorization.setEmail("email@email");
        userAuthorization.setPassword("pass");
        signUpController.signup(userAuthorization);
        ArgumentCaptor<UserAccount> userDetailsCaptor = ArgumentCaptor.forClass(UserAccount.class);
        verify(userDetailsService).signUp(userDetailsCaptor.capture());
        assertEquals("email@email", userDetailsCaptor.getValue().getEmail());
        assertEquals("pass", userDetailsCaptor.getValue().getPassword());
    }
}