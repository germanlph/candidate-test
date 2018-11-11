/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.repositories;

import org.naic.models.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepo {

    private List<Account> accounts;

    public Account findUser(String userName, String password) {
        List<Account> dummies = getDummyList();
        Account match = dummies.stream()
                .filter(account -> userName.equals(account.getUsername()) && password.equals(account.getPassword()))
                .findAny()
                .orElse(null);
        return match;
    }

    private List<Account> getDummyList() {
        List<Account> dummies = new ArrayList<>();
        Account dummy = new Account();
        dummy.setUsername("germanportillo");
        dummy.setPassword("Password!");
        dummy.setUserId(1);
        dummies.add(dummy);
        return dummies;
    }
}
