package com.globallogic.challenge.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneEntity {
  private Long number;
  private int cityCode;
  private String countryCode;
}
