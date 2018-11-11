/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.builders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.naic.models.User;
import org.naic.viewmodels.UserProfile;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBuilderTests {

    private UserProfile userProfile;

    @Before
    public void setUp() throws Exception {
        buildUserProfile();
    }
    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void buildUserTest() {
        User builtUser = new UserBuilder()
                .withUserProfile(userProfile)
                .build();
        assertThat(builtUser.getId(), is(1));
        assertThat(builtUser.getFirstName(), is("German"));
        assertThat(builtUser.getLastName(), is("Portillo"));
        assertThat(builtUser.getEmail(), is("germanlph@gmail.com"));
        assertThat(builtUser.getPhone(), is("(913) 375-8802"));

    }

    private void buildUserProfile() {
        userProfile = new UserProfile();
        userProfile.setFirstName("German");
        userProfile.setLastName("Portillo");
        userProfile.setEmail("germanlph@gmail.com");
        userProfile.setPhone("9133758802");
        userProfile.setId(1);
    }
}
