package com.globallogic.challenge.persistence.repositories;

import org.springframework.stereotype.Repository;

import com.globallogic.challenge.domain.entities.UserEntity;
import com.globallogic.challenge.domain.repositories.UserRepository;

@Repository
public class UserRepositoryImpl implements UserRepository {

  @Override
  public UserEntity save(UserEntity user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }

  @Override
  public UserEntity update(UserEntity user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public UserEntity getById(String id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getById'");
  }

}
