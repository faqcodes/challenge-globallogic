package com.globallogic.challenge.domain.services;

import com.globallogic.challenge.domain.models.SigninResponse;

public interface GetUserService {
  SigninResponse get(String id);
}
