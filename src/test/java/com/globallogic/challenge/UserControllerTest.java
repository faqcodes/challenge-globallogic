package com.globallogic.challenge;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.globallogic.challenge.controllers.UserController;
import com.globallogic.challenge.persistence.repositories.UserRepository;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  // @MockBean
  // private UserRepository userRepository;

  // Define constants for request bodies
  private static final String VALID_SIGNUP_REQUEST = "{\"name\": \"andrea\", \"email\" : \"andrea@test.cl\", \"password\" : \"andreaAN12\"}";
  private static final String INVALID_SIGNUP_REQUEST = "{\"name\": \"andrea\", \"password\" : \"andreaAN12\"}";

  // @Test
  // void whenPostRequestToControllerAndValidUser_thenCorrectResponse() throws
  // Exception {

  // mockMvc.perform(
  // MockMvcRequestBuilders
  // .post("/api/sign-up")
  // .content(VALID_SIGNUP_REQUEST)
  // .contentType(MediaType.APPLICATION_JSON))
  // .andExpect(MockMvcResultMatchers.status().isCreated());
  // }

  // @Test
  // void whenPostRequestToControllerAndInvalidUser_thenCorrectResponse() throws
  // Exception {

  // mockMvc.perform(
  // MockMvcRequestBuilders
  // .post("/api/sign-up")
  // .content(INVALID_SIGNUP_REQUEST)
  // .contentType(MediaType.APPLICATION_JSON))
  // .andExpect(MockMvcResultMatchers.status().isBadRequest());
  // }
}