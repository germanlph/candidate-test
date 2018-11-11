/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.repositories;

import org.springframework.stereotype.Repository;
import org.naic.models.User;

import java.util.ArrayList;
import java.util.List;


@Repository
public class UserRepo {

    private User appUser;

    public List<User> get() {
        return new ArrayList<>();
    }

    public User getById(int id) {
        if (appUser == null) {
            appUser = generateAppUser();
        }

        return appUser.getId() == id ? appUser : null;
    }

    public User save(User user) {
        //Store in database and return updated user
        return user;
    }


    private User generateAppUser() {
        User appUser = new User();
        appUser.setId(1);
        appUser.setFirstName("German");
        appUser.setLastName("Portillo");
        appUser.setPhone("9133758802");
        appUser.setEmail("germanlph@gmail.com");
        return appUser;
    }
}
