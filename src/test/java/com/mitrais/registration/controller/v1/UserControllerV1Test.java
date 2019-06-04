package com.mitrais.registration.controller.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mitrais.registration.constant.DataTest;
import com.mitrais.registration.entity.User;
import com.mitrais.registration.exception.UserException;
import com.mitrais.registration.model.ApiCodeReference;
import com.mitrais.registration.model.ApiResponse;
import com.mitrais.registration.model.UserRequest;
import com.mitrais.registration.service.UserService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerV1Test {
    @Mock
    private UserService userService;

    private MockMvc mvc;

    @InjectMocks
    private UserControllerV1 userControllerV1;

    private final User user = new User(DataTest.FIRST_NAME, DataTest.LAST_NAME, DataTest.PHONE, DataTest.EMAIL);
    private final UserRequest userRequest = new UserRequest(DataTest.FIRST_NAME, DataTest.LAST_NAME,DataTest.PHONE, DataTest.EMAIL);
    private final UserRequest userRequest1 = new UserRequest(DataTest.FIRST_NAME, DataTest.LAST_NAME,DataTest.PHONE, DataTest.INVALID_EMAIL);
    private final UserRequest userRequest2 = new UserRequest(DataTest.FIRST_NAME, DataTest.LAST_NAME,DataTest.INVALID_PHONE, DataTest.EMAIL);

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.standaloneSetup(userControllerV1).setControllerAdvice(new UserException()).build();
    }

    @Test
    public void createUser() throws Exception {
        Mockito.when(userService.createUser(userRequest)).thenReturn(user);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userRequest));
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        ApiResponse apiResponse = new ApiResponse(ApiCodeReference.SUCCESS, user);
        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
        Assert.assertEquals(new ObjectMapper().writeValueAsString(responseEntity.getBody()), response.getContentAsString());
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatus());
    }

    @Test
    public void createUserWithInvalidEmail() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userRequest1));
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        ApiResponse apiResponse = new ApiResponse(DataTest.EMAIL_INVALID, Collections.emptyList());
        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        Assert.assertEquals(new ObjectMapper().writeValueAsString(responseEntity.getBody()), response.getContentAsString());
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void createUserWithInvalidPhone() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userRequest2));
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        ApiResponse apiResponse = new ApiResponse(DataTest.PHONE_INVALID, Collections.emptyList());
        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        Assert.assertEquals(new ObjectMapper().writeValueAsString(responseEntity.getBody()), response.getContentAsString());
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void createUserWithExistedPhone() throws Exception {
        Mockito.when(userService.createUser(userRequest)).thenThrow(new Exception("USER0002"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userRequest));
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        ApiResponse apiResponse = new ApiResponse(DataTest.PHONE_EXISTED, Collections.emptyList());
        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        Assert.assertEquals(new ObjectMapper().writeValueAsString(responseEntity.getBody()), response.getContentAsString());
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }

    @Test
    public void createUserWithExistedEmail() throws Exception {
        Mockito.when(userService.createUser(userRequest)).thenThrow(new Exception("USER0001"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(userRequest));
        MockHttpServletResponse response = mvc.perform(requestBuilder).andReturn().getResponse();
        ApiResponse apiResponse = new ApiResponse(DataTest.EMAIL_EXISTED, Collections.emptyList());
        ResponseEntity<ApiResponse> responseEntity = new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
        Assert.assertEquals(new ObjectMapper().writeValueAsString(responseEntity.getBody()), response.getContentAsString());
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
}
