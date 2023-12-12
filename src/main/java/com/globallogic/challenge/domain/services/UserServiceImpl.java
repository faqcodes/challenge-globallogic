package com.globallogic.challenge.domain.services;

import org.springframework.stereotype.Service;

import com.globallogic.challenge.domain.models.SignupRequest;
import com.globallogic.challenge.domain.models.SignupResponse;
import com.globallogic.challenge.domain.repositories.UserRepository;

@Service
public class UserServiceImpl<T> implements CrudService<SignupRequest, SignupResponse> {

  private UserRepository userRepository;

  public UserServiceImpl(T repository) {
    this.userRepository = (UserRepository) repository;
  }

  @Override
  public SignupResponse create(SignupRequest createRequest) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'create'");
  }

  @Override
  public SignupResponse getById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

}
