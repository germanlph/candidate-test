/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.naic.interfaces.AccountService;
import org.naic.interfaces.UserService;
import org.naic.models.Account;
import org.naic.repositories.AccountRepo;
import org.naic.viewmodels.LoginViewModel;
import org.naic.viewmodels.UserProfile;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTests {

    private LoginViewModel loginViewModel;

    private UserProfile userProfile;

    private Account account;

    @InjectMocks
    private AccountService accountService = new AccountServiceImpl();

    @Mock
    private AccountRepo accountRepo;

    @Mock
    private UserService userService = new UserServiceImpl();

    @Before
    public void setUp() throws Exception {
        buildLoginViewModel();
        buildAccount();
        buildUserProfile();
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void loginTest() throws Exception {
        Mockito.when(accountRepo.findUser(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(account);
        Mockito.when(userService.getById(Mockito.anyInt())).thenReturn(userProfile);

        try {
            UserProfile userProfile = accountService.login(loginViewModel);
            assertThat(userProfile, notNullValue(UserProfile.class));
        }
        catch (Exception ex) {
            assertThat(ex.getMessage(), is(""));
        }

        loginViewModel.setPassword("");

        try {
            userProfile = accountService.login(loginViewModel);
        }
        catch (Exception ex) {
            assertThat(ex.getMessage(), is("Invalid password"));
        }

        loginViewModel.setPassword("Password!");
        loginViewModel.setUsername("");

        try {
            userProfile = accountService.login(loginViewModel);
        }
        catch (Exception ex) {
            assertThat(ex.getMessage(), is("Invalid username"));
        }

        Mockito.when(accountRepo.findUser(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(null);

        loginViewModel.setUsername("germanportillo");
        try {
            userProfile = accountService.login(loginViewModel);
        }
        catch (Exception ex) {
            assertThat(ex.getMessage(), is("User not found"));
        }
    }

    @Test
    public void validateLoginTest() {}

    private void buildLoginViewModel() {
        loginViewModel = new LoginViewModel();
        loginViewModel.setUsername("germanportillo");
        loginViewModel.setPassword("Password!");
    }

    private void buildAccount() {
        account = new Account();
        account.setUserId(1);
        account.setUsername("germanportillo");
        account.setPassword("Password!");
    }

    private void buildUserProfile() {
        userProfile = new UserProfile();
        userProfile.setId(1);
    }
}
