/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.builders;

import org.naic.models.User;
import org.naic.viewmodels.UserProfile;

/**
 * Builds a {@link UserProfile} object with a {@link User}
 */
public class UserProfileBuilder {

    private User user;


    public UserProfileBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public UserProfile build() {
        UserProfile userProfile = new UserProfile();
        userProfile.setFirstName(user.getFirstName());
        userProfile.setLastName(user.getLastName());
        userProfile.setEmail(user.getEmail());
        userProfile.setId(user.getId());
        userProfile.setPhone(user.getPhone());
        return userProfile;
    }
}
