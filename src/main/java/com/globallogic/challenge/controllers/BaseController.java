package com.globallogic.challenge.controllers;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.globallogic.challenge.domain.models.ErrorModel;
import com.globallogic.challenge.domain.models.ErrorResponse;

public class BaseController {

  // Error MethodArgumentNotValid
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
    var errors = new ArrayList<ErrorModel>();

    e.getBindingResult().getAllErrors().forEach(error -> {
      var errorMessage = error.getDefaultMessage();

      var errorModel = new ErrorModel(
          LocalDateTime.now(),
          0,
          errorMessage);

      errors.add(errorModel);
    });

    var errorResponse = new ErrorResponse(errors);

    return ResponseEntity.badRequest().body(errorResponse);
  }

  // Error general
  @ExceptionHandler(Exception.class)
  public ResponseEntity<?> handleException(Exception e) {
    var errors = new ArrayList<ErrorModel>();

    errors.add(
        new ErrorModel(
            LocalDateTime.now(),
            400,
            e.getMessage()));

    var errorResponse = new ErrorResponse(errors);

    return ResponseEntity.badRequest().body(errorResponse);
  }
}
