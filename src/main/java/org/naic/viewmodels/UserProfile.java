/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.viewmodels;

import java.io.Serializable;
import java.util.Map;

/**
 * User's basic profile
 *
 */
public class UserProfile implements Serializable
{

    private String firstName;   // required
    private String middleInit;
    private String lastName;    // required
    private String email;       // required
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private String phone;       // form (###) ###-#### but save a numeric
    private int id;
    private Map<String, String> validationErrorMap;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleInit() {
        return middleInit;
    }

    public void setMiddleInit(String middleInit) {
        this.middleInit = middleInit;
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

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Map<String, String> getValidationErrorMap() {
        return validationErrorMap;
    }

    public void setValidationErrorMap(Map<String, String> validationErrorMap) {
        this.validationErrorMap = validationErrorMap;
    }
}
