/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.viewmodels;

import org.naic.models.Account;

public class LoginViewModel {

    private String username;
    private String password;
    private Account account;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
