package com.hedzic.ajdin.endorsementtracker.service;

import com.hedzic.ajdin.endorsementtracker.entity.Pilot;
import com.hedzic.ajdin.endorsementtracker.entity.UserAccount;
import com.hedzic.ajdin.endorsementtracker.entity.UserPasswordReset;
import com.hedzic.ajdin.endorsementtracker.repository.PilotRepository;
import com.hedzic.ajdin.endorsementtracker.repository.UserAccountRepository;
import com.hedzic.ajdin.endorsementtracker.repository.UserPasswordResetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private PilotRepository pilotRepository;

    @Mock
    private UserPasswordResetRepository userPasswordResetRepository;

    @Mock
    private DateProvider dateProvider;

    @Mock
    private EmailService emailService;

    private UserAccount userAccount;

    @Before
    public void setUp() throws Exception {
        userAccount = new UserAccount("email@email", "1234");
        when(dateProvider.getCurrentTime()).thenReturn(LocalDateTime.now());
    }

    @Test
    public void signUpCallsRepositorySave() {
        userDetailsServiceImpl.signUp(userAccount);
        verify(userAccountRepository).findByEmail("email@email");
    }

    @Test
    public void signUpCallsSaveWhenUserDoesNotExist() throws Exception {
        userDetailsServiceImpl.signUp(userAccount);
        verify(userAccountRepository).save(any(UserAccount.class));
    }

    @Test
    public void signUpDoesNotCallSaveWhenUserExits() throws Exception {
        when(userAccountRepository.findByEmail(anyString())).thenReturn(userAccount);
        userDetailsServiceImpl.signUp(userAccount);
        verify(userAccountRepository, times(0)).save(userAccount);
    }

    @Test
    public void signUpCallsSaveWithEncryptedPassword() throws Exception {
        when(bCryptPasswordEncoder.encode(userAccount.getPassword())).thenReturn("encryptedPassword");
        userDetailsServiceImpl.signUp(userAccount);
        ArgumentCaptor<UserAccount> userAccountCaptor = ArgumentCaptor.forClass(UserAccount.class);
        verify(userAccountRepository).save(userAccountCaptor.capture());
        assertEquals("encryptedPassword", userAccountCaptor.getValue().getPassword());
    }

    @Test
    public void signUpEncryptsPassword() throws Exception {
        when(userAccountRepository.findByEmail(anyString())).thenReturn(userAccount);
        userDetailsServiceImpl.signUp(userAccount);
        verify(userAccountRepository, times(0)).save(userAccount);
    }

    @Test
    public void signUpSavesNewPilotProfile() throws Exception {
        when(userAccountRepository.save(any(UserAccount.class))).thenReturn(userAccount);
        userDetailsServiceImpl.signUp(userAccount);
        ArgumentCaptor<Pilot> captor = ArgumentCaptor.forClass(Pilot.class);
        verify(pilotRepository).save(captor.capture());
        assertEquals(userAccount.getEmail(), captor.getValue().getUserAccount().getEmail());
    }

    @Test
    public void signUpReturnsSavedUser() throws Exception {
        Pilot pilot = new Pilot();
        when(pilotRepository.save(any(Pilot.class))).thenReturn(pilot);
        Pilot returnedPilot = userDetailsServiceImpl.signUp(userAccount);
        assertEquals(pilot, returnedPilot);
    }

    @Test
    public void loadUserByUserNameLoadsEmailFromUserRepository() throws Exception {
        UserAccount userAccount = new UserAccount("hello@hello.com", "pass");
        when(userAccountRepository.findByEmail("hello@hello.com")).thenReturn(userAccount);
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(userAccount.getEmail());
        assertEquals(userAccount.getEmail(), userDetails.getUsername());
        assertEquals(userAccount.getPassword(), userDetails.getPassword());
    }

    @Test
    public void requestForgottenPasswordEmailforForSavesUniqueIdToUserAccountForResetLink() throws Exception {
        when(userAccountRepository.findByEmail(anyString())).thenReturn(userAccount);
        LocalDateTime currentTime = LocalDateTime.now();
        when(dateProvider.getCurrentTime()).thenReturn(currentTime);
        userDetailsServiceImpl.requestForgottenPasswordEmailFor("hello@hello.com");
        ArgumentCaptor<UserPasswordReset> captor = ArgumentCaptor.forClass(UserPasswordReset.class);
        verify(userPasswordResetRepository).save(captor.capture());
        assertEquals(36, captor.getValue().getPasswordResetToken().length());
        assertEquals(currentTime.plusMinutes(15), captor.getValue().getPasswordResetExpirationDate());
    }

    @Test
    public void requestForgottenPasswordEmailforForSavesPasswordResetToUserAccount() throws Exception {
        when(userAccountRepository.findByEmail(anyString())).thenReturn(userAccount);
        userDetailsServiceImpl.requestForgottenPasswordEmailFor("hello@hello.com");
        ArgumentCaptor<UserAccount> captor = ArgumentCaptor.forClass(UserAccount.class);
        ArgumentCaptor<UserPasswordReset> passwordResetCaptor = ArgumentCaptor.forClass(UserPasswordReset.class);
        verify(userAccountRepository).save(captor.capture());
        verify(userPasswordResetRepository).save(passwordResetCaptor.capture());
        assertEquals(passwordResetCaptor.getValue(), captor.getValue().getUserPasswordReset());
    }

    @Test
    public void requestForgottenPasswordEmailforDoesNothingWhenUserDoesNotExist() throws Exception {
        userDetailsServiceImpl.requestForgottenPasswordEmailFor("hello@hello.com");
        verify(userAccountRepository, times(0)).save(any(UserAccount.class));
        verify(userPasswordResetRepository, times(0)).save(any(UserPasswordReset.class));
    }

    @Test
    public void requestForgottenPasswordEmailforCallsEmailSenderWithEmailAndToken() throws Exception {
        when(userAccountRepository.findByEmail(anyString())).thenReturn(userAccount);
        userDetailsServiceImpl.requestForgottenPasswordEmailFor("hello@hello.com");
        verify(emailService).sendPasswordResetEmailTo(eq("hello@hello.com"), anyString());
    }
}