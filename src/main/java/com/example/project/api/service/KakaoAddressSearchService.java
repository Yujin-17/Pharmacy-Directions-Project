package com.example.project.api.service;

import com.example.project.api.dto.KakaoApiResponseDto;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
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

  @Retryable(
      value = {RuntimeException.class}, // 상황에 따라 더 구체적인 전처리도 가능하다
      maxAttempts = 2, // 2번의 재시도를 허용 (기본값 3번)
      backoff = @Backoff(delay = 2000) // 얼마만큼 딜레이를 줄건지 = 2초
  )
  public KakaoApiResponseDto requestAddressSearch(String address){

    if(ObjectUtils.isEmpty(address)) return null;

    URI uri = kakaoUriBuilderService.builderUriByAddressSearch(address); // 실제 카카오 API를 호출하기 위한 api

    HttpHeaders headers = new HttpHeaders();
    headers.set(HttpHeaders.AUTHORIZATION, "KakaoAK " + kakaoRestApiKey);
    HttpEntity httpEntity = new HttpEntity<>(headers);

    // kakao api 호출
    return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, KakaoApiResponseDto.class).getBody();
  }
  @Recover
  public KakaoApiResponseDto recover(RuntimeException e, String address) {
    log.error("All the retries failed. address: {}, error: {}", address, e.getMessage());
    return null;
  }// @Recover를 사용할때의 주의점은, 원래 메소드의 리턴 타입을 맞춰줘야한다.

}
