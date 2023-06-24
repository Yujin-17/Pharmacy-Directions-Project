package com.example.project.direction.entity;

import com.example.project.BaseTimeEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "direction")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Direction extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // 고객의 정보
  private String inputAddress;
  private double inputLatitude;
  private double inputLongitude;

  // 약국
  private String targetPharmacyName;
  private String targetAddress;
  private double targetLatitude;
  private double targetLongitude;

  // 고객 주소와 약국 주소 사이의 거리
  private double distance;

}
