package com.globallogic.challenge.domain.services;

import java.time.LocalDateTime;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

    if (userData == null) {
      return null;
    }

    final var lastLogin = LocalDateTime.now();

    userData.setLastLogin(lastLogin);

    final var userResult = userRepository.save(userData);

    return mapper.map(userResult, SigninResponse.class);
  }

}
