package com.mitrais.registration.service;

import com.mitrais.registration.entity.User;
import com.mitrais.registration.model.UserRequest;

public interface UserService {
    User createUser(UserRequest user) throws Exception;
}
