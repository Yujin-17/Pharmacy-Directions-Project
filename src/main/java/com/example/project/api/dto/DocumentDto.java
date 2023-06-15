package com.example.project.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

  @JsonProperty("address_name") // 전체 지번 주소 or 전체 도로명 주소
  private String addressName;

  @JsonProperty("y")  // 위도
  private double latitude;

  @JsonProperty("x")  // 경도
  private double longitude;

}
