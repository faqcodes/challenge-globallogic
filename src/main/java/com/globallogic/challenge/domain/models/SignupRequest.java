package com.globallogic.challenge.domain.models;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.globallogic.challenge.domain.entities.PhoneEntity;

import lombok.Data;

@Data
public class SignupRequest {
  private final String name;

  @NotNull(message = "Debe ingresar un correo electrónico")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$", message = "El correo electrónico no es válido")
  private final String email;

  @NotNull(message = "Debe ingresar una contraseña")
  @Pattern(regexp = "^(?=(?:[^A-Z]*[A-Z]){1}[^A-Z]*)(?=(?:\\\\D*\\\\d){2})(?=[a-z]{8,12}$)", message = "La contraseña no es válida")
  private final String password;

  private final PhoneEntity[] phones;
}
