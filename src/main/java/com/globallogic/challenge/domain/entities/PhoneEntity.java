package com.globallogic.challenge.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhoneEntity {
  private Long number;
  private int cityCode;
  private String countryCode;
}
