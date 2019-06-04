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
    private static final String PHONE_EXISTED = "USER0002";
    private static final String EMAIL_EXITED = "USER0001";

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public User createUser(UserRequest userRequest) throws Exception {
        User user = objectMapper.convertValue(userRequest, User.class);
        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            throw new Exception(PHONE_EXISTED);
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new Exception(EMAIL_EXITED);
        }
        return userRepository.save(user);
    }
}
