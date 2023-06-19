package com.example.project.api.service;

import java.net.URI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
public class KakaoUriBuilderService {

  private static final String KAKAO_LOCAL_SEARCH_ADDRESS_URL = "https://dapi.kakao.com/v2/local/search/address.json";

  private static final String KAKAO_LOCAL_SEARCH_SEARCH_URL = "https://dapi.kakao.com/v2/local/search/category.json";

  public URI builderUriByAddressSearch(String address){
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCAL_SEARCH_ADDRESS_URL);
    uriBuilder.queryParam("query", address);

    URI uri = uriBuilder.build().encode().toUri();
    log.info("[KakaoUriBuilderService builderUriByAddressSearch] address: {}, uri:{}", address, uri);

    return uri;
  }

  public URI buildUriByCategorySearch(double latitude, double longitude, double radius, String category) {

    double meterRadius = radius * 1000;

    UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(KAKAO_LOCAL_SEARCH_SEARCH_URL);
    uriComponentsBuilder.queryParam("category_group_code", category);
    uriComponentsBuilder.queryParam("x", longitude);
    uriComponentsBuilder.queryParam("y", latitude);
    uriComponentsBuilder.queryParam("radius", meterRadius);
    uriComponentsBuilder.queryParam("sort", "distance");

    URI uri = uriComponentsBuilder.build().encode().toUri();

    log.info("[KakaoAddressSearchService builderUriByCategorySearch] uri: {} ", uri);

    return uri;
  }
}
