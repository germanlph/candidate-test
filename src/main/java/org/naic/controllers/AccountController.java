/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.controllers;

import org.naic.interfaces.AccountService;
import org.naic.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.naic.viewmodels.LoginViewModel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private UserService userService;

    /**
     * Log in to the app
     * @param loginViewModel of type {@link LoginViewModel}
     * @return {@link org.naic.viewmodels.UserProfile}
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginViewModel loginViewModel) {

        try {
            return ResponseEntity.ok().body(accountService.login(loginViewModel));

        }
        catch (Exception ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
