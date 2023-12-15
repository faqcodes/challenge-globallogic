package com.globallogic.challenge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;

import com.globallogic.challenge.configurations.security.JwtTokenProvider;
import com.globallogic.challenge.domain.models.SigninResponse;
import com.globallogic.challenge.domain.services.GetUserServiceImpl;
import com.globallogic.challenge.persistence.entities.PhoneData;
import com.globallogic.challenge.persistence.entities.UserData;
import com.globallogic.challenge.persistence.repositories.UserRepository;

class GetUserServiceTest {

  private ModelMapper modelMapper;
  private UserRepository userRepository;
  private GetUserServiceImpl getService;

  @BeforeEach
  void setUp() {
    // Preparar el repositorio mockado
    modelMapper = mock(ModelMapper.class);
    userRepository = mock(UserRepository.class);
    getService = new GetUserServiceImpl(userRepository);
  }

  @Test
  void testSignin() {
    final var userId = "bdc37bba-575c-4515-b4a7-b8df6a4504ed";
    final var token = new JwtTokenProvider().createToken(userId);

    final var phoneData = new PhoneData(0l, 123456789l, 34, "+56");
    final var phonesData = new PhoneData[] { phoneData };

    final var data = UserData.builder()
        .id(userId)
        .token(token)
        .isActive(true)
        .name("andrea")
        .email("andrea@test1.cl")
        .password("andreaAN1")
        .phones(phonesData)
        .build();

    final var signinResponse = SigninResponse.builder()
        .id(userId)
        .token(token)
        .isActive(true)
        .build();

    when(userRepository.getById(userId)).thenReturn(data);
    when(modelMapper.map(data, SigninResponse.class)).thenReturn(signinResponse);

    // final var response = getService.get(userId);

    // // Assert
    // assertNotNull(response);
    // assertEquals(response.getId(), data.getId());
    // assertEquals(response.getToken(), data.getToken());
    // assertEquals(response.getIsActive(), data.getIsActive());
    // assertEquals(response.getName(), data.getName());
    // assertEquals(response.getEmail(), data.getEmail());
    // assertEquals(response.getPassword(), data.getPassword());

    // verify(userRepository, times(1)).getById(userId);
  }

}