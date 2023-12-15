package com.globallogic.challenge.domain.models;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.globallogic.challenge.domain.entities.PhoneEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {
  private final String name;

  @NotNull(message = "Debe ingresar un correo electrónico")
  @NotBlank(message = "Debe ingresar un correo electrónico")
  @NotEmpty(message = "Debe ingresar un correo electrónico")
  @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,6}$", message = "El correo electrónico no es válido")
  private final String email;

  @NotNull(message = "Debe ingresar una contraseña")
  @NotBlank(message = "Debe ingresar una contraseña")
  @NotEmpty(message = "Debe ingresar una contraseña")
  @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,12}$", message = "La contraseña no es válida")
  private final String password;

  private final PhoneEntity[] phones;
}