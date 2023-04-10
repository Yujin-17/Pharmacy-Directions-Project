package com.example.project.api.service;

import com.example.project.api.dto.KakaoApiResponseDto;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

@Slf4j // 로그
@Service // 컴포넌트
@RequiredArgsConstructor // 생성자 주입
public class KakaoAddressSearchService {

  // 의존성 주입
  private final RestTemplate restTemplate;
  private final KakaoUriBuilderService kakaoUriBuilderService;

  @Value("${kakao.rest.api.key}") // 환경변수 가져오기
  private String kakaoRestApiKey;

  public KakaoApiResponseDto requestAddressSearch(String address){

    if(ObjectUtils.isEmpty(address)) return null;

    URI uri = kakaoUriBuilderService.builderUriByAddressSearch(address);

    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
    HttpEntity httpEntity = new HttpEntity<>(headers);

    // kakao api 호출
    return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody();
  }

}
