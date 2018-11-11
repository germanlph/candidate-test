/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.builders;

import org.naic.models.User;
import org.naic.viewmodels.UserProfile;

/**
 * Builds a {@link User} object with a {@link UserProfile}
 */
public class UserBuilder {

    private UserProfile userProfile;

    public UserBuilder withUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
        return this;
    }

    public User build() {
        User user = new User();
        user.setFirstName(userProfile.getFirstName());
        user.setLastName(userProfile.getLastName());
        user.setEmail(userProfile.getEmail());
        user.setPhone(userProfile.getPhone());
        user.setId(userProfile.getId());
        return user;
    }
}
