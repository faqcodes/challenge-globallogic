package com.globallogic.challenge.domain.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.globallogic.challenge.configurations.security.JwtTokenProvider;
import com.globallogic.challenge.domain.models.SigninResponse;
import com.globallogic.challenge.persistence.repositories.UserRepository;

import io.jsonwebtoken.lang.Assert;

@Service
public class GetUserServiceImpl implements GetUserService {

  private final UserRepository userRepository;

  public GetUserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public SigninResponse get(String userId) {
    final var mapper = new ModelMapper();
    final var userData = userRepository.getById(userId);

    Assert.notNull(userData, "El usuario no se encuentra registrado");

    final var lastLogin = LocalDateTime.now();
    userData.setLastLogin(lastLogin);

    final var userResult = userRepository.save(userData);
    final var response = mapper.map(userResult, SigninResponse.class);

    // Generate Token
    final var token = new JwtTokenProvider().createToken(userId);
    response.setToken(token);

    return response;
  }

}
