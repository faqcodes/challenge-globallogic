package com.globallogic.challenge.domain.models;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.globallogic.challenge.domain.entities.PhoneEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class SigninResponse extends RepresentationModel<SigninResponse> {
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
