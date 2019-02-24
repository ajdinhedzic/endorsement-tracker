package com.hedzic.ajdin.endorsementtracker.service;

import com.hedzic.ajdin.endorsementtracker.entity.UserAccount;
import com.hedzic.ajdin.endorsementtracker.repository.UserAccountRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AppUserDetailsServiceTest {

    @InjectMocks
    private AppUserDetailsService appUserDetailsService;

    @Mock
    private UserAccountRepository userAccountRepository;

    @Mock
    private EncryptionProvider encryptionProvider;
    private UserAccount userAccount;

    @Before
    public void setUp() throws Exception {
        userAccount = new UserAccount("email@email", "1234");
    }

    @Test
    public void signUpCallsRepositorySave() {
        appUserDetailsService.signUp(userAccount);
        verify(userAccountRepository).findByEmail("email@email");
    }

    @Test
    public void signUpCallsSaveWhenUserDoesNotExist() throws Exception {
        appUserDetailsService.signUp(userAccount);
        verify(userAccountRepository).save(any(UserAccount.class));
    }

    @Test
    public void signUpDoesNotCallSaveWhenUserExits() throws Exception {
        when(userAccountRepository.findByEmail(anyString())).thenReturn(userAccount);
        appUserDetailsService.signUp(userAccount);
        verify(userAccountRepository, times(0)).save(userAccount);
    }

    @Test
    public void signUpCallsSaveWithEncryptedPassword() throws Exception {
        when(encryptionProvider.encrypt(eq(userAccount.getPassword()), anyString())).thenReturn("encryptedPassword");
        appUserDetailsService.signUp(userAccount);
        ArgumentCaptor<UserAccount> userAccountCaptor = ArgumentCaptor.forClass(UserAccount.class);
        verify(userAccountRepository).save(userAccountCaptor.capture());
        assertEquals("encryptedPassword", userAccountCaptor.getValue().getPassword());
    }

    @Test
    public void signUpEncryptsPassword() throws Exception {
        when(userAccountRepository.findByEmail(anyString())).thenReturn(userAccount);
        appUserDetailsService.signUp(userAccount);
        verify(userAccountRepository, times(0)).save(userAccount);
    }
}