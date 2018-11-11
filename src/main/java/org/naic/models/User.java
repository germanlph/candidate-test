/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.models;

import org.naic.viewmodels.PhoneNumber;

/**
 * Represents a user's profile information
 */
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private PhoneNumber phone;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone.toString();
    }

    public void setPhone(String number) {
        this.phone = new PhoneNumber(number);
    }
}
