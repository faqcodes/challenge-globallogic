package com.globallogic.challenge.domain.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.globallogic.challenge.configurations.security.JwtTokenProvider;
import com.globallogic.challenge.domain.entities.UserEntity;
import com.globallogic.challenge.domain.models.SigninResponse;
import com.globallogic.challenge.persistence.repositories.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class GetUserServiceImpl implements GetUserService {

  private final UserRepository userRepository;

  public GetUserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public SigninResponse get(String userId) {

    final var mapper = new ModelMapper();
    final var jwtTokenProvider = new JwtTokenProvider();

    final var userData = userRepository.getById(userId);

    Assert.isTrue(
        userData != null,
        "El usuario no se encuentra registrado");

    final var lastLogin = LocalDateTime.now();
    final var token = jwtTokenProvider.createToken(userId);

    userData.setToken(token);
    userData.setLastLogin(lastLogin);

    final var userResult = userRepository.save(userData);

    return mapper.map(userResult, SigninResponse.class);
  }

}
