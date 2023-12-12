package com.globallogic.challenge.domain.entities;

import java.time.LocalDateTime;

public class UserEntity {
  private String id;
  private LocalDateTime created;
  private LocalDateTime lastLogin;
  private String token;
  private Boolean isActive;
  private String name;
  private String email;
  private String password;
  private PhoneEntity[] phones;
}
