/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.interfaces;

import org.naic.viewmodels.LoginViewModel;
import org.naic.viewmodels.UserProfile;

public interface AccountService {

    UserProfile login(LoginViewModel loginViewModel) throws Exception;
}
