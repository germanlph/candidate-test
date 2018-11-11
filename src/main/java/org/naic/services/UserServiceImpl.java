/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.services;

import org.naic.Sanitizer;
import org.naic.builders.UserBuilder;
import org.naic.builders.UserProfileBuilder;
import org.naic.interfaces.UserService;
import org.naic.models.User;
import org.naic.repositories.UserRepo;
import org.naic.viewmodels.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    /**
     * Gets user view model by account id
     * @param id of user account
     * @return {@link UserProfile}
     * @throws Exception for failed validation
     */
    public UserProfile getById(int id) throws Exception {

        User match = userRepo.getById(id);
        if (match == null) {
            throw new IllegalArgumentException("User not found");
        }
        return new UserProfileBuilder().withUser(match).build();

    }

    /**
     * Update a user profile
     * @param updating {@link UserProfile} to update
     * @return updated {@link UserProfile}
     * @throws IllegalArgumentException when invalid
     */
    public UserProfile update(UserProfile updating) throws IllegalArgumentException {

        ValidateUserViewModel(updating);
        User updated = userRepo.getById(updating.getId());
        if (!updated.getEmail().equals(updating.getEmail())) {
            checkForDuplicates(updating);
        }
        if (!updating.getValidationErrorMap().isEmpty()) {
            throw new IllegalArgumentException();
        }
        updated = new UserBuilder().withUserProfile(updating).build();
        updated = userRepo.save(updated);
        return new UserProfileBuilder().withUser(updated).build();

    }

    private void checkForDuplicates(UserProfile updating) {
        //Check for uniqueness in the fictitious db
        if (updating.getEmail().equals("email@email.com")) {
            updating.getValidationErrorMap().put("emailDuplicate", "Email is already in use");
        }
    }

    private void ValidateUserViewModel(UserProfile updating) {

        updating.setValidationErrorMap(new HashMap<>());
        if (updating.getId() == 0) {
            updating.getValidationErrorMap().put("userId", "User id is required");
        }
        if (StringUtils.isEmpty(updating.getFirstName())) {
            updating.getValidationErrorMap().put("firstName", "First name required");
        }
        if (StringUtils.isEmpty(updating.getLastName())) {
            updating.getValidationErrorMap().put("lastName", "Last name required");
        }
        if (StringUtils.isEmpty(updating.getEmail())) {
            updating.getValidationErrorMap().put("email", "Email required");
        }
        if (StringUtils.isEmpty(updating.getPhone())) {
            updating.getValidationErrorMap().put("phone", "Phone required");
        }
        if (!Sanitizer.SanitizeInput(updating.getFirstName())) {
            updating.getValidationErrorMap().put("firstNameValid", "First name is invalid");
        }
        if (!Sanitizer.SanitizeInput(updating.getLastName())) {
            updating.getValidationErrorMap().put("lastNameValid", "Last name is invalid");
        }
        if (!Sanitizer.SanitizeInput(updating.getEmail())) {
            updating.getValidationErrorMap().put("emailValid", "Email is invalid");
        }
        if (!updating.getEmail().matches("^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$")) {
            updating.getValidationErrorMap().put("emailValid", "Email is invalid");
        }
    }
}
