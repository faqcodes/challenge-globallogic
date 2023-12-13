package com.globallogic.challenge.domain.services;

import org.springframework.stereotype.Service;

import com.globallogic.challenge.domain.models.SignupRequest;
import com.globallogic.challenge.domain.models.SignupResponse;
import com.globallogic.challenge.domain.repositories.UserRepository;

public class CreateUserServiceImpl implements CreateUserService {

  private UserRepository userRepository;

  public CreateUserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public CrudService<SignupRequest, SignupResponse> create() {
    return new UserServiceImpl<>(userRepository);
  }

}
