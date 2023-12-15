package com.globallogic.challenge.domain.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneEntity {
  private Long number;
  private int cityCode;
  private String countryCode;
}
