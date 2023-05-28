package com.example.project.pharmacy.service;

import com.example.project.pharmacy.entity.Pharmacy;
import com.example.project.pharmacy.repository.PharmacyRepository;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j // 롬복의 로그
@Service
@RequiredArgsConstructor // 롬복의 생성자 주입
public class PharmacyRepositoryService {
  private final PharmacyRepository pharmacyRepository;

  @Transactional
  public void updateAddress(Long id, String address){
    Pharmacy entity = pharmacyRepository.findById(id).orElse(null); // findById가 optional로 되어있어서 null 일때 null로 처리해줌.

    if(Objects.isNull(entity)){ // entity가 null이면 진행할 필요가 없어 로그로 띄운다.
      log.error("[PharmacyRepositoryService updateAddress] not fount id: {}", id);
      return;
    }

    entity.changePharmacyAddress(address); // entity값을 정상적으로 찾아왔으면, 주소를 변경
  }

  // for test -> Dirty Checking을 위해서
  public void updateAddressWithoutTransaction(Long id, String address){
    Pharmacy entity = pharmacyRepository.findById(id).orElse(null); // findById가 optional로 되어있어서 null 일때 null로 처리해줌.

    if(Objects.isNull(entity)){ // entity가 null이면 진행할 필요가 없어 로그로 띄운다.
      log.error("[PharmacyRepositoryService updateAddress] not fount id: {}", id);
      return;
    }

    entity.changePharmacyAddress(address); // entity값을 정상적으로 찾아왔으면, 주소를 변경
  }

}
