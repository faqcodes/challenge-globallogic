package com.globallogic.challenge.domain.services;

import com.globallogic.challenge.domain.models.SignupRequest;
import com.globallogic.challenge.domain.models.SignupResponse;

public interface CreateUserService {
  SignupResponse create(SignupRequest createRequest);
}
