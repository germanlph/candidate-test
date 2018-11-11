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
import org.naic.interfaces.UserService;
import org.naic.models.User;
import org.naic.repositories.UserRepo;
import org.naic.viewmodels.UserProfile;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Mock
    private UserRepo userRepo;

    private User user = new User();

    private UserProfile userProfile = new UserProfile();

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGet() {
        UserProfile testProfile;

        Mockito.when(userRepo.getById(Mockito.anyInt()))
                .thenReturn(null);
        try {
            testProfile = userService.getById(1);
        }
        catch (Exception ex) {
            assertThat(ex.getMessage(), is("User not found"));
        }

        user.setLastName("Portillo");
        user.setFirstName("German");
        user.setId(1);
        user.setEmail("germanlph@gmail.com");
        user.setPhone("913-375-8802");
        Mockito.when(userRepo.getById(Mockito.anyInt()))
                .thenReturn(user);
        try {
            testProfile = userService.getById(1);
            assertThat(testProfile.getId(), is(1));
            assertThat(testProfile.getFirstName(), is("German"));
            assertThat(testProfile.getLastName(), is("Portillo"));
            assertThat(testProfile.getPhone(), is("(913) 375-8802"));
            assertThat(testProfile.getEmail(), is("germanlph@gmail.com"));
        }
        catch (Exception ex) {
            assertThat(ex.getMessage(), is(""));
        }

    }

    @Test
    public void testUpdate() {
        try {
            userProfile = userService.update(userProfile);
        }
        catch (Exception ex) {
            assertThat(userProfile.getValidationErrorMap().get("userId"), is("User id is required"));
            assertThat(userProfile.getValidationErrorMap().get("firstName"), is("First name required"));
            assertThat(userProfile.getValidationErrorMap().get("lastName"), is("Last name required"));
            assertThat(userProfile.getValidationErrorMap().get("email"), is("Email required"));
            assertThat(userProfile.getValidationErrorMap().get("phone"), is("Phone required"));
        }

        userProfile.setFirstName("select");
        userProfile.setLastName("alter");
        userProfile.setEmail("drop");

        try {
            userProfile = userService.update(userProfile);
        }
        catch (Exception ex) {
            assertThat(userProfile.getValidationErrorMap().get("firstNameValid"), is("First name is invalid"));
            assertThat(userProfile.getValidationErrorMap().get("lastNameValid"), is("Last name is invalid"));
            assertThat(userProfile.getValidationErrorMap().get("emailValid"), is("Email is invalid"));
        }

        userProfile.setEmail("blahblah");

        try {
            userProfile = userService.update(userProfile);
        }
        catch (Exception ex) {
            assertThat(userProfile.getValidationErrorMap().get("emailValid"), is("Email is invalid"));
        }

        User duplicateEmail = new User();
        duplicateEmail.setEmail("germanlph@gmail.com");
        Mockito.when(userRepo.getById(Mockito.anyInt())).thenReturn(duplicateEmail);
        userProfile.setEmail("email@email.com");

        try {
            userProfile = userService.update(userProfile);
        }
        catch (Exception ex) {
            assertThat(userProfile.getValidationErrorMap().get("emailDuplicate"), is("Email is already in use"));
        }

    }
}
