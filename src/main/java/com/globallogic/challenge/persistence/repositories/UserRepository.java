package com.globallogic.challenge.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.globallogic.challenge.persistence.entities.UserData;

@Repository
public interface UserRepository extends JpaRepository<UserData, String> {

  @Query(value = "SELECT CASE WHEN COUNT(u.id) > 0 THEN true ELSE false END FROM USERS u WHERE email=:email", nativeQuery = true)
  boolean existsByEmail(@Param("email") String email);

}