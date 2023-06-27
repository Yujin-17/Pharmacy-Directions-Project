package com.example.project.direction.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OutputDto {

  private String PharmacyName;
  private String PharmacyAddress;
  private String directionUrl;
  private String roadViewUrl;
  private String distance;

}
