package com.globallogic.challenge.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.globallogic.challenge.domain.services.GetUserServiceImpl;
import com.globallogic.challenge.persistence.entities.PhoneData;
import com.globallogic.challenge.persistence.entities.UserData;
import com.globallogic.challenge.persistence.repositories.UserRepository;

@SpringBootTest
class GetUserServiceTest {

  private UserRepository userRepository;
  private GetUserServiceImpl getUserService;

  @BeforeEach
  void setUp() {
    // Preparar el repositorio mockado
    userRepository = mock(UserRepository.class);
    getUserService = new GetUserServiceImpl(userRepository);
  }

  @Test
  void when_getUser_with_all_data_then_expect_all_data() {
    final var userId = "bdc37bba-575c-4515-b4a7-b8df6a4504ed";
    final var phoneData = new PhoneData(0l, 123456789l, 34, "+56");
    final var phonesData = new PhoneData[] { phoneData };

    final var data = UserData.builder()
        .id(userId)
        .created(LocalDateTime.now())
        .isActive(true)
        .name("andrea")
        .email("andrea@test.cl")
        .password("andreaA12")
        .phones(phonesData)
        .build();

    when(userRepository.getById(userId)).thenReturn(data);
    when(userRepository.save(data)).thenReturn(data);

    final var response = getUserService.get(userId);

    // Verificar que el resultado sea el esperado
    assertNotNull(response);
    assertNotNull(response.getId());
    assertNotNull(response.getCreated());
    assertNotNull(response.getLastLogin());
    assertNotNull(response.getName());
    assertNotNull(response.getEmail());
    assertNotNull(response.getPassword());
    assertNotNull(response.getPhones());
    assertTrue(response.getIsActive());

    verify(userRepository, times(1)).getById(userId);
    verify(userRepository, times(1)).save(data);
  }

  @Test
  void when_getUser_with_optional_data_then_noExpect_all_data() {
    final var userId = "bdc37bba-575c-4515-b4a7-b8df6a4504ed";

    final var data = UserData.builder()
        .id(userId)
        .created(LocalDateTime.now())
        .isActive(true)
        .email("andrea@test.cl")
        .password("andreaA12")
        .build();

    when(userRepository.getById(userId)).thenReturn(data);
    when(userRepository.save(data)).thenReturn(data);

    final var response = getUserService.get(userId);

    // Verificar que el resultado sea el esperado
    assertNotNull(response);
    assertNotNull(response.getId());
    assertNotNull(response.getCreated());
    assertNotNull(response.getLastLogin());
    assertNotNull(response.getEmail());
    assertNotNull(response.getPassword());
    assertTrue(response.getIsActive());

    assertNull(response.getName());
    assertNull(response.getPhones());

    verify(userRepository, times(1)).getById(userId);
    verify(userRepository, times(1)).save(data);
  }

  @Test
  void when_getUser_and_notExist_then_expect_exception() {
    final var userId = "bdc37bba-575c-4515-b4a7-b8df6a45-XXX";

    when(userRepository.getById(userId)).thenReturn(null);

    // Verificar que el resultado sea el esperado
    assertThrows(IllegalArgumentException.class, () -> {
      getUserService.get(userId);
    });

    verify(userRepository, times(1)).getById(userId);
    verify(userRepository, never()).save(any());
  }
}