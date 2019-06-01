package com.mitrais.registration.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.registration.entity.User;
import com.mitrais.registration.model.UserRequest;
import com.mitrais.registration.repository.UserRepository;
import com.mitrais.registration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(UserRequest userRequest) {
        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.convertValue(userRequest, User.class);
        return userRepository.save(user);
    }

    @Override
    public boolean isExistedPhone(String phone) {
        return userRepository.findUserByPhoneNumber(phone) != null;
    }

    @Override
    public boolean isExistedEmail(String email) {
        return userRepository.findUserByEmail(email) != null;
    }
}
