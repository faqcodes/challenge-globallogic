package com.globallogic.challenge;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.globallogic.challenge.configurations.security.JwtTokenProvider;
import com.globallogic.challenge.domain.entities.PhoneEntity;
import com.globallogic.challenge.domain.models.SignupRequest;
import com.globallogic.challenge.domain.services.CreateUserServiceImpl;
import com.globallogic.challenge.persistence.entities.PhoneData;
import com.globallogic.challenge.persistence.entities.UserData;
import com.globallogic.challenge.persistence.repositories.UserRepository;

class CreateUserServiceTest {

  private UserRepository userRepository;
  private CreateUserServiceImpl createService;

  @BeforeEach
  void setUp() {
    // Preparar el repositorio mockado
    userRepository = mock(UserRepository.class);
    createService = new CreateUserServiceImpl(userRepository);
  }

  @Test
  void testSignup() {
    // Preparar los datos de entrada para el método create
    final var phone = new PhoneEntity(123456789l, 34, "+56");
    final var phones = new PhoneEntity[] { phone };
    final var createRequest = SignupRequest.builder()
        .name("andrea")
        .email("andrea@test1.cl")
        .password("andreaAN12")
        .phones(phones)
        .build();

    // Configurar el comportamiento esperado del repositorio mockado
    final var userId = UUID.randomUUID().toString();
    final var token = new JwtTokenProvider().createToken(userId);

    final var phoneData = new PhoneData(0l, 123456789l, 34, "+56");
    final var phonesData = new PhoneData[] { phoneData };

    final var savedEntity = UserData.builder()
        .id(userId)
        .token(token)
        .isActive(true)
        .name(createRequest.getName())
        .email(createRequest.getEmail())
        .password(createRequest.getPassword())
        .phones(phonesData)
        .build();

    when(userRepository.save(any(UserData.class))).thenReturn(savedEntity);

    // Ejecutar el método create
    var response = createService.create(createRequest);

    // Verificar que el resultado sea el esperado
    Assertions.assertNotNull(response);
    Assertions.assertEquals(userId, response.getId());
    Assertions.assertEquals(token, response.getToken());
    Assertions.assertTrue(response.getIsActive());

    // Verificar que el método save fue llamado en el repositorio con los parámetros
    // correctos
    verify(userRepository).save(any(UserData.class));
  }
}