package com.globallogic.challenge.domain.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.globallogic.challenge.domain.entities.PhoneEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {
  private final String name;

  @NotBlank(message = "Debe ingresar un correo electrónico")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$", message = "El correo electrónico no es válido")
  private final String email;

  @NotBlank(message = "Debe ingresar una contraseña")
  @Pattern(regexp = "^(?=(?:[^A-Z]*[A-Z]){1})(?=(?:[^\\d]*\\d){2})(?=(?:[^A-Za-z]*[a-z]){0,})([A-Za-z\\d]{8,12})$", message = "La contraseña no es válida")
  private final String password;

  private final PhoneEntity[] phones;
}