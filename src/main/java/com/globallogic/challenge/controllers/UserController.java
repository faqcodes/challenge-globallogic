package com.globallogic.challenge.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.challenge.domain.models.SigninResponse;
import com.globallogic.challenge.domain.models.SignupRequest;
import com.globallogic.challenge.domain.models.SignupResponse;
import com.globallogic.challenge.domain.services.CreateUserService;
import com.globallogic.challenge.domain.services.GetUserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Validated
@RestController
@CrossOrigin(origins = "*") // temporal
@RequestMapping("/api")
public class UserController extends BaseController {

  private final GetUserService getUserService;
  private final CreateUserService createUserService;

  public UserController(
      GetUserService getUserService,
      CreateUserService createUserService) {
    this.getUserService = getUserService;
    this.createUserService = createUserService;
  }

  @PostMapping("/sign-up")
  public ResponseEntity<SignupResponse> signup(@Valid @RequestBody SignupRequest signupRequest) {
    var createdUri = URI.create("");

    var signupResponse = createUserService.create(signupRequest);

    // 201
    return ResponseEntity
        .created(createdUri)
        .body(signupResponse);
  }

  @PostMapping("/login")
  public ResponseEntity<SigninResponse> signin() {

    var authentication = SecurityContextHolder.getContext().getAuthentication();
    var userId = authentication.getName();
    log.info("GET USERID: {}", userId);
    var signinResponse = getUserService.get(userId);

    // 200
    return ResponseEntity
        .ok()
        .body(signinResponse);
  }
}
