package com.globallogic.challenge.domain.models;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorModel {
  private final LocalDateTime timestamp;
  private final int codigo;
  private final String detail;
}
