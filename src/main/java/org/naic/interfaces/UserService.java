/*
 * Copyright 2018. National Association of Insurance Commissioners.
 */

package org.naic.interfaces;

import org.naic.viewmodels.UserProfile;

public interface UserService {

    UserProfile getById(int id) throws Exception;

    UserProfile update(UserProfile userViewModel) throws Exception;
}
