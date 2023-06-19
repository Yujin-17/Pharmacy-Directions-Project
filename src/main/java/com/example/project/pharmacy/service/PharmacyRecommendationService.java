package com.example.project.pharmacy.service;

import com.example.project.api.dto.DocumentDto;
import com.example.project.api.dto.KakaoApiResponseDto;
import com.example.project.api.service.KakaoAddressSearchService;
import com.example.project.direction.service.DirectionService;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class PharmacyRecommendationService {

  private final KakaoAddressSearchService kakaoAddressSearchService;
  private final DirectionService directionService;

  public void recommendationPharmacyList(String address){

    KakaoApiResponseDto kakaoApiResponseDto = kakaoAddressSearchService.requestAddressSearch(address);

    if(Objects.isNull(kakaoApiResponseDto) || CollectionUtils.isEmpty(kakaoApiResponseDto.getDocumentList())){
      log.error("{PharmacyRecommendationService recommendPharmacyList fail Input address: {}", address);
      return;
    }

    DocumentDto documentDto = kakaoApiResponseDto.getDocumentList().get(0);

    List<Direction> directionList = directionService.buildDirectionList(documentDto);

    directionService.saveAll(directionList);

  }

}
