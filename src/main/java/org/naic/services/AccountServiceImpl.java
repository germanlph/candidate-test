/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.services;

import org.naic.Sanitizer;
import org.naic.interfaces.AccountService;
import org.naic.interfaces.UserService;
import org.naic.models.Account;
import org.naic.repositories.AccountRepo;
import org.naic.viewmodels.LoginViewModel;
import org.naic.viewmodels.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private UserService userService;

    /**
     * Logs users in
     * @param loginViewModel {@link LoginViewModel}
     * @return {@link UserProfile}
     * @throws Exception for validation failures
     */
    public UserProfile login(LoginViewModel loginViewModel) throws Exception {

        ValidateLogin(loginViewModel);

        Account match = accountRepo.findUser(loginViewModel.getUsername(), loginViewModel.getPassword());
        if (match == null) {
            throw new IllegalArgumentException("User not found");
        } else {
            return userService.getById(match.getUserId());
        }
    }

    private void ValidateLogin(LoginViewModel loginViewModel) throws Exception {

        if (loginViewModel.getUsername() == null || StringUtils.isEmpty(loginViewModel.getUsername())) {
            throw new IllegalArgumentException("Invalid username");
        }

        if (loginViewModel.getPassword() == null || StringUtils.isEmpty(loginViewModel.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }

        //Sanitize with regex. Optionally implement parameterized query to db
        Sanitizer.SanitizeInput(loginViewModel.getUsername());
        Sanitizer.SanitizeInput(loginViewModel.getPassword());

    }
}
