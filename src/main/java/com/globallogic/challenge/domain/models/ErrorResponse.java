package com.globallogic.challenge.domain.models;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponse {
  private final ArrayList<ErrorModel> error;
}
