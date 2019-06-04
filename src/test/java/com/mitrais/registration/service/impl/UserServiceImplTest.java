package com.mitrais.registration.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.registration.constant.DataTest;
import com.mitrais.registration.entity.User;
import com.mitrais.registration.model.UserRequest;
import com.mitrais.registration.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ObjectMapper objectMapper;

    private final User user = new User(DataTest.FIRST_NAME, DataTest.LAST_NAME, DataTest.PHONE, DataTest.EMAIL);
    private final UserRequest userRequest = new UserRequest(DataTest.FIRST_NAME, DataTest.LAST_NAME, DataTest.PHONE, DataTest.EMAIL);

    @Test
    public void createUser() throws Exception {
        when(objectMapper.convertValue(userRequest, User.class)).thenReturn(user);
        when(userRepository.existsByPhoneNumber(any())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(any())).thenReturn(false);
        when(userRepository.save(user)).thenReturn(user);
        User user = userService.createUser(userRequest);
        Assert.assertEquals(DataTest.EMAIL, user.getEmail());
        Assert.assertEquals(DataTest.FIRST_NAME, user.getFirstName());
        Assert.assertEquals(DataTest.LAST_NAME, user.getLastName());
        Assert.assertEquals(DataTest.PHONE, user.getPhoneNumber());
    }

    @Test(expected = Exception.class)
    public void createUserWithPhoneException() throws Exception {
        when(objectMapper.convertValue(userRequest, User.class)).thenReturn(user);
        when(userRepository.existsByPhoneNumber(any())).thenReturn(true);
        userService.createUser(userRequest);
    }

    @Test(expected = Exception.class)
    public void createUserWithEmailException() throws Exception {
        when(objectMapper.convertValue(userRequest, User.class)).thenReturn(user);
        when(userRepository.existsByPhoneNumber(any())).thenReturn(false);
        when(userRepository.existsByPhoneNumber(any())).thenReturn(true);
        userService.createUser(userRequest);
    }
}
