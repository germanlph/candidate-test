/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.builders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.naic.models.User;
import org.naic.viewmodels.UserProfile;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileBuilderTests {

    private User user;

    @Before
    public void setUp() throws Exception {
        buildUser();
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void buildUserProfileTest() {
        UserProfile userProfile = new UserProfileBuilder()
                .withUser(user)
                .build();
        assertThat(userProfile.getFirstName(), is("German"));
        assertThat(userProfile.getLastName(), is("Portillo"));
        assertThat(userProfile.getEmail(), is("germanlph@gmail.com"));
        assertThat(userProfile.getPhone(), is("(913) 375-8802"));
        assertThat(userProfile.getId(), is(1));
    }

    private void buildUser() {
        user = new User();
        user.setFirstName("German");
        user.setLastName("Portillo");
        user.setEmail("germanlph@gmail.com");
        user.setPhone("9133758802");
        user.setId(1);
    }
}
