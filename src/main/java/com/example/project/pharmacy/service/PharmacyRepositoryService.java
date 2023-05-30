package com.example.project.pharmacy.service;

import com.example.project.pharmacy.entity.Pharmacy;
import com.example.project.pharmacy.repository.PharmacyRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Slf4j // 롬복의 로그
@Service
@RequiredArgsConstructor // 롬복의 생성자 주입
public class PharmacyRepositoryService {

  private final PharmacyRepository pharmacyRepository;

  // self invocation test
  public void bar(List<Pharmacy> pharmacyList){
    log.info("bar CurrentTransactionName: "+ TransactionSynchronizationManager.getCurrentTransactionName());
    foo(pharmacyList); // 약국 리스트를 받아 foo라는 메소드에 전달
  }

  // self inovocation test
  @Transactional
  public void foo(List<Pharmacy> pharmacyList){
    log.info("foo CurrentTransactionName: " + TransactionSynchronizationManager.getCurrentTransactionName());
    pharmacyList.forEach(pharmacy -> {
      pharmacyRepository.save(pharmacy); // 저장 후
      throw new RuntimeException("error"); // 에러발생 // @Transactional 에서 exception 발생하면 자동으로 롤백하는 정책을 가짐. -> 이 코드에서는 정상적으로 트랜잭션이 실행되면 롤백이 되야함.(exception처리를 했기 때문)
    });
  }

  // read only test


  @Transactional
  public void updateAddress(Long id, String address) {
    Pharmacy entity = pharmacyRepository.findById(id)
        .orElse(null); // findById가 optional로 되어있어서 null 일때 null로 처리해줌.

    if (Objects.isNull(entity)) { // entity가 null이면 진행할 필요가 없어 로그로 띄운다.
      log.error("[PharmacyRepositoryService updateAddress] not fount id: {}", id);
      return;
    }

    entity.changePharmacyAddress(address); // entity값을 정상적으로 찾아왔으면, 주소를 변경
  }

  // for test -> Dirty Checking을 위해서
  public void updateAddressWithoutTransaction(Long id, String address) {
    Pharmacy entity = pharmacyRepository.findById(id)
        .orElse(null); // findById가 optional로 되어있어서 null 일때 null로 처리해줌.

    if (Objects.isNull(entity)) { // entity가 null이면 진행할 필요가 없어 로그로 띄운다.
      log.error("[PharmacyRepositoryService updateAddress] not fount id: {}", id);
      return;
    }

    entity.changePharmacyAddress(address); // entity값을 정상적으로 찾아왔으면, 주소를 변경
  }

}