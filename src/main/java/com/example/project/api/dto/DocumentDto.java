package com.example.project.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto {

  @JsonProperty("place_Name") // 카테고리에 따라 다르지만, 약국 카테고리일경우, 약국명이 지정됨
  private String placeName;
  @JsonProperty("address_name") // 전체 지번 주소 or 전체 도로명 주소
  private String addressName;

  @JsonProperty("y")  // 위도
  private double latitude;

  @JsonProperty("x")  // 경도
  private double longitude;

  @JsonProperty("distance") // 거리
  private double distance;
}
