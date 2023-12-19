package com.globallogic.challenge.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.globallogic.challenge.configurations.security.JwtTokenProvider;
import com.globallogic.challenge.domain.models.SigninResponse;
import com.globallogic.challenge.domain.services.CreateUserService;
import com.globallogic.challenge.domain.services.GetUserService;

@AutoConfigureMockMvc
@WebMvcTest(UserController.class)
class SigninControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CreateUserService createUserService;

  @MockBean
  private GetUserService getUserService;

  @InjectMocks
  private UserController userController;

  // Define constants for request bodies
  private static final String VALID_SIGNIN_TOKEN = "Bearer ";
  private static final String INVALID_SIGNIN_TOKEN = "Bearer ";

  @Test
  void whenSigninAndValidToken_thenCorrectResponse() throws Exception {
    final var jwtTokenProvider = new JwtTokenProvider();

    when(getUserService.get(any(String.class))).thenReturn(new SigninResponse());

    final var userId = UUID.randomUUID().toString();
    final var token = jwtTokenProvider.createToken(userId);
    final var bearer = VALID_SIGNIN_TOKEN + token;

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/login")
            .header("Authorization", bearer))
        .andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  void whenSigninAndInvalidToken_thenCorrectResponse() throws Exception {

    when(getUserService.get(any(String.class))).thenReturn(new SigninResponse());

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/login")
            .header("Authorization", INVALID_SIGNIN_TOKEN))
        .andExpect(MockMvcResultMatchers.status().isForbidden());
  }

  @Test
  void whenSigninAndWithoutToken_thenCorrectResponse() throws Exception {

    when(getUserService.get(any(String.class))).thenReturn(new SigninResponse());

    mockMvc.perform(
        MockMvcRequestBuilders
            .post("/api/login")
            .header("Authorization", ""))
        .andExpect(MockMvcResultMatchers.status().isForbidden());
  }

}