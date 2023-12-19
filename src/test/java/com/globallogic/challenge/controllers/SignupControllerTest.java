package com.globallogic.challenge.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.globallogic.challenge.domain.models.SignupRequest;
import com.globallogic.challenge.domain.models.SignupResponse;
import com.globallogic.challenge.domain.services.CreateUserService;
import com.globallogic.challenge.domain.services.GetUserService;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
class SignupControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CreateUserService createUserService;

  @MockBean
  private GetUserService getUserService;

  @InjectMocks
  private UserController userController;

  // Define constants for request bodies
  private static final String VALID_SIGNUP_REQUEST = "{\"name\": \"andrea\", \"email\" : \"andrea@test.cl\", \"password\" : \"andreaA12\"}";
  private static final String EMAIL_EXISTS_REQUEST = "{\"name\": \"andrea\", \"email\" : \"andrea@test.cl\", \"password\" : \"andreaA12\"}";
  private static final String INVALID_SIGNUP_REQUEST = "{\"name\": \"andrea\", \"password\" : \"andreaA12\"}";
  private static final String INVALID_EMAIL_REQUEST = "{\"name\": \"andrea\", \"email\" : \"andrea@test\", \"password\" : \"andreaA12\"}";
  private static final String EMPTY_EMAIL_REQUEST = "{\"name\": \"andrea\", \"password\" : \"andreaA12\"}";
  private static final String INVALID_PASSWORD_REQUEST = "{\"name\": \"andrea\", \"email\" : \"andrea@test.cl\", \"password\" : \"anXRFdreaANasdf12\"}";
  private static final String EMPTY_PASSWORD_REQUEST = "{\"name\": \"andrea\", \"email\" : \"andrea@test.cl\"}";

  @Test
  void whenSignupAndValidData_thenCorrectResponse() throws Exception {

    when(createUserService.create(any(SignupRequest.class))).thenReturn(new SignupResponse());

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/sign-up")
            .content(VALID_SIGNUP_REQUEST)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  @Test
  void whenSignupAndInvalidData_thenCorrectResponse() throws Exception {

    when(createUserService.create(any(SignupRequest.class))).thenReturn(new SignupResponse());

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/sign-up")
            .content(INVALID_SIGNUP_REQUEST)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error[0].timestamp",
            Matchers.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error[0].codigo", Matchers.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error[0].detail",
            Matchers.notNullValue()));
  }

  @Test
  void whenSignupAndInvalidEmail_thenCorrectMessage() throws Exception {

    when(createUserService.create(any(SignupRequest.class))).thenReturn(new SignupResponse());

    var expectValue = "El correo electrónico no es válido";

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/sign-up")
            .content(INVALID_EMAIL_REQUEST)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error[0].detail",
            Matchers.is(expectValue)));
  }

  @Test
  void whenSignupAndEmptyEmail_thenCorrectMessage() throws Exception {

    when(createUserService.create(any(SignupRequest.class))).thenReturn(new SignupResponse());

    var expectValue = "Debe ingresar un correo electrónico";

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/sign-up")
            .content(EMPTY_EMAIL_REQUEST)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error[0].detail",
            Matchers.is(expectValue)));
  }

  @Test
  void whenSignupAndInvalidPassword_thenCorrectMessage() throws Exception {

    when(createUserService.create(any(SignupRequest.class))).thenReturn(new SignupResponse());

    var expectValue = "La contraseña no es válida";

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/sign-up")
            .content(INVALID_PASSWORD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error[0].detail",
            Matchers.is(expectValue)));
  }

  @Test
  void whenSignupAndEmptyPassword_thenCorrectMessage() throws Exception {

    when(createUserService.create(any(SignupRequest.class))).thenReturn(new SignupResponse());

    var expectValue = "Debe ingresar una contraseña";

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/sign-up")
            .content(EMPTY_PASSWORD_REQUEST)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error", Matchers.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error[0].detail",
            Matchers.is(expectValue)));
  }

  @Test
  void whenSignupAndEmailExists_thenCorrectMessage() throws Exception {
    var expectValue = "El correo ya se encuentra registrado";

    when(createUserService.create(any(SignupRequest.class)))
        .thenThrow(new IllegalArgumentException(expectValue));

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/sign-up")
            .content(EMAIL_EXISTS_REQUEST)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest())
        .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error",
            Matchers.notNullValue()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.error[0].detail",
            Matchers.is(expectValue)));
  }
}