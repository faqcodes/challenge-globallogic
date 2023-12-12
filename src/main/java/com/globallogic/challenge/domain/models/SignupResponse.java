package com.globallogic.challenge.domain.models;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
public class SignupResponse extends RepresentationModel<SignupResponse> {
  private final String id;
  private final LocalDateTime created;
  private final LocalDateTime lastLogin;
  private final String token;
  private final Boolean isActive;
}
