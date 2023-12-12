package com.globallogic.challenge.persistence.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "users")
public class UserDataEntity {
  @Id
  private String id;
  private LocalDateTime created;
  private LocalDateTime lastLogin;
  private String token;
  private Boolean isActive;
  private String name;
  private String email;
  private String password;
  private PhoneDataEntity[] phones;

  public UserDataEntity() {
    super();
  }
}
