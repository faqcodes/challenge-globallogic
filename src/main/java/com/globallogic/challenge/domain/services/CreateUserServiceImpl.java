package com.globallogic.challenge.domain.services;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.globallogic.challenge.configurations.security.JwtTokenProvider;
import com.globallogic.challenge.domain.entities.PhoneEntity;
import com.globallogic.challenge.domain.entities.UserEntity;
import com.globallogic.challenge.domain.models.SignupRequest;
import com.globallogic.challenge.domain.models.SignupResponse;
import com.globallogic.challenge.persistence.entities.UserData;
import com.globallogic.challenge.persistence.repositories.UserRepository;

@Service
public class CreateUserServiceImpl implements CreateUserService {

  private final UserRepository userRepository;

  public CreateUserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public SignupResponse create(SignupRequest createRequest) {

    Assert.isTrue(
        !userRepository.existsByEmail(createRequest.getEmail()),
        "El correo ya se encuentra registrado");

    final var mapper = new ModelMapper();

    // Get ID
    final var userId = UUID.randomUUID().toString();
    // Create Phone for Entity: map phone model to var
    final var length = createRequest.getPhones() == null ? 0 : createRequest.getPhones().length;
    final var phones = length > 0 ? Arrays.copyOf(createRequest.getPhones(), length) : new PhoneEntity[] {};

    // Create User Entity
    final var userEntity = UserEntity.builder()
        .id(userId)
        .created(LocalDateTime.now())
        .lastLogin(LocalDateTime.now())
        .name(createRequest.getName())
        .email(createRequest.getEmail())
        .password(createRequest.getPassword())
        .phones(phones)
        .isActive(true)
        .build();

    final var userData = mapper.map(userEntity, UserData.class);
    final var userResult = userRepository.save(userData);

    final var signupResponse = mapper.map(userResult, SignupResponse.class);

    // Generate Token
    final var token = new JwtTokenProvider().createToken(userId);
    signupResponse.setToken(token);

    return signupResponse;
  }
}
