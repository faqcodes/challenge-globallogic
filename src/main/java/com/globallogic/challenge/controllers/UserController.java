package com.globallogic.challenge.controllers;

import java.net.URI;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.challenge.configurations.security.JwtTokenProvider;
import com.globallogic.challenge.domain.models.SignupRequest;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Validated
@RestController
@CrossOrigin(origins = "*") // temporal
@RequestMapping("/api")
public class UserController extends BaseController {

  // private CreateUserService createUserService;

  // public UserController(CreateUserService createUserService) {
  // this.createUserService = createUserService;
  // }

  @PostMapping("/sign-up")
  public ResponseEntity<String> signup(@Valid @RequestBody SignupRequest signupRequest) {
    var createdUri = URI.create("");

    var tokenProvider = new JwtTokenProvider();
    var token = tokenProvider.createToken(signupRequest.getEmail());

    log.info(token);

    // 201
    return ResponseEntity
        .created(createdUri)
        .body(token);
  }

  @PostMapping("/login")
  public ResponseEntity<String> signin() {

    // 200
    return ResponseEntity
        .ok()
        .body("OK");
  }
}
