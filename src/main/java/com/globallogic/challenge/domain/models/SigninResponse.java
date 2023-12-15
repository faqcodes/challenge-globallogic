package com.globallogic.challenge.domain.models;

import java.time.LocalDateTime;

import org.springframework.hateoas.RepresentationModel;

import com.globallogic.challenge.domain.entities.PhoneEntity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class SigninResponse extends RepresentationModel<SigninResponse> {
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
