package com.globallogic.challenge.persistence.repositories;

import org.springframework.data.repository.CrudRepository;

import com.globallogic.challenge.persistence.entities.UserDataEntity;

public interface UserCrudRepository extends CrudRepository<UserDataEntity, String> {}
