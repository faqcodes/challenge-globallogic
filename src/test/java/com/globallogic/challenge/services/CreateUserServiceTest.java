package com.globallogic.challenge.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import com.globallogic.challenge.domain.entities.PhoneEntity;
import com.globallogic.challenge.domain.models.SignupRequest;
import com.globallogic.challenge.domain.models.SignupResponse;
import com.globallogic.challenge.domain.services.CreateUserServiceImpl;
import com.globallogic.challenge.persistence.entities.PhoneData;
import com.globallogic.challenge.persistence.entities.UserData;
import com.globallogic.challenge.persistence.repositories.UserRepository;

@SpringBootTest
class CreateUserServiceTest {
  private ModelMapper modelMapper;
  private UserRepository userRepository;
  private CreateUserServiceImpl createUserService;

  @BeforeEach
  void setUp() {
    // Preparar el repositorio mockado
    modelMapper = mock(ModelMapper.class);
    userRepository = mock(UserRepository.class);
    createUserService = new CreateUserServiceImpl(userRepository);
  }

  @Test
  void when_signup_with_all_valid_data_then_ok() {
    final var token = "JWT_TOKEN";

    // Preparar los datos de entrada para el método create
    final var phone = new PhoneEntity(123456789l, 34, "+56");
    final var phones = new PhoneEntity[] { phone };
    final var request = SignupRequest.builder()
        .name("andrea")
        .email("andrea@test1.cl")
        .password("andreaA12")
        .phones(phones)
        .build();

    // Configurar el comportamiento esperado del repositorio mockado
    final var userId = UUID.randomUUID().toString();
    final var phoneData = new PhoneData(0l, 123456789l, 34, "+56");
    final var phonesData = new PhoneData[] { phoneData };

    final var data = UserData.builder()
        .id(userId)
        .created(LocalDateTime.now())
        .lastLogin(LocalDateTime.now())
        .isActive(true)
        .name(request.getName())
        .email(request.getEmail())
        .password(request.getPassword())
        .phones(phonesData)
        .build();

    final var mapper = SignupResponse.builder()
        .id(userId)
        .created(data.getCreated())
        .lastLogin(data.getLastLogin())
        .token(token)
        .isActive(data.getIsActive())
        .build();

    when(userRepository.save(any(UserData.class))).thenReturn(data);
    when(modelMapper.map(UserData.class, SignupResponse.class)).thenReturn(mapper);

    // Ejecutar el método create
    var response = createUserService.create(request);

    // Verificar que el resultado sea el esperado
    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getCreated());
    Assertions.assertNotNull(response.getLastLogin());
    Assertions.assertNotNull(response.getToken());
    Assertions.assertEquals(userId, response.getId());
    Assertions.assertTrue(response.getIsActive());

    // Verificar que el método save fue llamado en el repositorio con los parámetros
    // correctos
    verify(userRepository).save(any(UserData.class));
  }

  @Test
  void when_signup_with_optional_valid_data_then_ok() {
    final var token = "JWT_TOKEN";

    // Preparar los datos de entrada para el método create
    final var request = SignupRequest.builder()
        .email("andrea@test1.cl")
        .password("andreaA12")
        .build();

    // Configurar el comportamiento esperado del repositorio mockado
    final var userId = UUID.randomUUID().toString();

    final var data = UserData.builder()
        .id(userId)
        .created(LocalDateTime.now())
        .lastLogin(LocalDateTime.now())
        .isActive(true)
        .email(request.getEmail())
        .password(request.getPassword())
        .build();

    final var signupResponse = SignupResponse.builder()
        .id(userId)
        .created(LocalDateTime.now())
        .lastLogin(LocalDateTime.now())
        .token(token)
        .isActive(true)
        .build();

    when(userRepository.save(any(UserData.class))).thenReturn(data);
    when(modelMapper.map(data, SignupResponse.class)).thenReturn(signupResponse);

    // Ejecutar el método create
    var response = createUserService.create(request);

    // Verificar que el resultado sea el esperado
    Assertions.assertNotNull(response);
    Assertions.assertNotNull(response.getCreated());
    Assertions.assertNotNull(response.getLastLogin());
    Assertions.assertNotNull(response.getToken());
    Assertions.assertEquals(userId, response.getId());
    Assertions.assertTrue(response.getIsActive());

    // Verificar que el método save fue llamado en el repositorio con los parámetros
    // correctos
    verify(userRepository).save(any(UserData.class));
  }

  @Test
  void when_signup_and_emailExits_then_expect_exception() {
    // Preparar los datos de entrada para el método create
    final var email = "andrea@test.cl";

    final var request = SignupRequest.builder()
        .email("andrea@test.cl")
        .password("andreaA12")
        .build();

    when(userRepository.existsByEmail(email)).thenReturn(true);

    // Verificar que el resultado sea el esperado
    assertThrows(IllegalArgumentException.class, () -> {
      createUserService.create(request);
    });

    verify(userRepository, times(1)).existsByEmail(email);
    verify(userRepository, never()).save(any());
  }

}