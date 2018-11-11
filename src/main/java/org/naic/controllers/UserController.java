/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.controllers;

import org.naic.interfaces.UserService;
import org.naic.viewmodels.UserProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Retrieve user profile
     * @param id integer for user id
     * @return {@link UserProfile}
     */
    @GetMapping("/profile")
    @ResponseBody
    public ResponseEntity getProfile(int id) {

        try {
            UserProfile user = userService.getById(id);
            return ResponseEntity.ok().body(user);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    /**
     * Update user profile
     * @param updating {@link UserProfile}
     * @return updated {@link UserProfile}
     */
    @PutMapping("/profile")
    @ResponseBody
    public ResponseEntity<UserProfile> updateProfile(@RequestBody UserProfile updating) {

        try {
            UserProfile updated = userService.update(updating);
            return ResponseEntity.ok().body(updated);
        }
        catch (Exception ex) {
            return new ResponseEntity<>(updating, HttpStatus.BAD_REQUEST);
        }

    }

}
