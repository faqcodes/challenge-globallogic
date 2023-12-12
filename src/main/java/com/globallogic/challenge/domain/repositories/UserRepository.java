package com.globallogic.challenge.domain.repositories;

import com.globallogic.challenge.domain.entities.UserEntity;

public interface UserRepository {
  UserEntity save(UserEntity user);
  UserEntity update(UserEntity user);
  UserEntity getById(String id);
}
