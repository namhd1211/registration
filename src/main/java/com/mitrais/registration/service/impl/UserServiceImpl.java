package com.mitrais.registration.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
    public User createUser(UserRequest userRequest) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        User user = mapper.convertValue(userRequest, User.class);
        if (userRepository.existsByPhoneNumber(userRequest.getPhoneNumber())) {
            throw new Exception("Mobile number should be unique.");
        }
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new Exception("Email should be unique");
        }
        return userRepository.save(user);
    }
}
