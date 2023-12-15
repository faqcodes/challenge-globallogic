package com.globallogic.challenge.domain.entities;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserEntity {
  private final String id;
  private final LocalDateTime created;
  private final LocalDateTime lastLogin;
  private final String token;
  private final Boolean isActive;
  private final String name;
  private final String email;
  private final String password;
  private final PhoneEntity[] phones;
}
