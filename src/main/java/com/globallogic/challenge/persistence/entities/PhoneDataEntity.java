package com.globallogic.challenge.persistence.entities;

public class PhoneDataEntity {
  private Long number;
  private int cityCode;
  private String countryCode;

  public PhoneDataEntity(Long number, int cityCode, String countryCode) {
    this.number = number;
    this.cityCode = cityCode;
    this.countryCode = countryCode;
  }
}
