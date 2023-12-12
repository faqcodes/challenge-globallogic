package com.globallogic.challenge.domain.services;

public interface CrudService<T, U> {
  U create(T createRequest);
  U getById(String id);
}
