package com.banquito.scheduledcollections.service;

import java.math.BigDecimal;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.banquito.core.account.dto.TransactionDTO;
import com.banquito.scheduledcollections.config.BaseURLValues;
import com.banquito.scheduledcollections.dao.CollectionOrderRepository;
import com.banquito.scheduledcollections.dao.CollectionRepository;
import com.banquito.scheduledcollections.dto.JournalDTO;
import com.banquito.scheduledcollections.enums.CollectionOrderEnum;
import com.banquito.scheduledcollections.exception.NotFoundException;
import com.banquito.scheduledcollections.model.CollectionOrder;
import com.banquito.scheduledcollections.model.Collection;

import org.springframework.http.HttpEntity;
import org.springframework.kafka.annotation.KafkaListener;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollectionOrderService {
  private final CollectionOrderRepository collectionOrderRepository;
  private final CollectionRepository collectionRepository;
  private RestTemplate restTemplate = new RestTemplate();
  private final BaseURLValues baseURLs;

  public Collection findCollectionById(String collectionId) {
    Optional<Collection> collectionOpt = this.collectionRepository.findByInternalId(collectionId);
    if (collectionOpt.isPresent()) {
      return collectionOpt.get();
    } else {
      return null;
    }
  }

  public void saveCollectionOrder(CollectionOrder collectionOrder){
    this.collectionOrderRepository.save(collectionOrder);
  }

  public void saveCollection(Collection collection){
    this.collectionRepository.save(collection);
  }

  /*public List<CollectionOrder> getCollectionOrderRecurrementToPay() {
    return this.collectionOrderRepository
        .findByStateAndPaymentWayAndStartCollectionDateGreaterThanAndEndCollectionDateLessThan(
            CollectionOrderEnum.TOPAY.getValue(),PaymentWayEnum.RECURRENT.getValue(),new Date(),new Date());
  }*/

  public List<CollectionOrder> getCollectionOrderRecurrementToPay() {
    return this.collectionOrderRepository
        .findByStateOrderByCollectionId(
            CollectionOrderEnum.TOPAY.getValue());
  }

  public CollectionOrder findByInternalId(String internalId) {
    Optional<CollectionOrder> collectionOrderOpt =
        this.collectionOrderRepository.findByInternalId(internalId);
    return collectionOrderOpt.orElse(null);
  }
  
  @KafkaListener(
      topics = "transaction_recurrement",
      groupId = "fooCollR",
      containerFactory = "fooCollRListener")
  public void payCollectionOrderResponse(TransactionDTO transactionDTO) throws NotFoundException {
    String internalId = transactionDTO.getReference();
    BigDecimal fullPaymentValue = transactionDTO.getAmount();
    CollectionOrder collectionOrderDb = this.findByInternalId(internalId);
    if (collectionOrderDb == null) {
      log.warn("Orden de cobro con Id: {} no encontrado ", internalId);
      throw new NotFoundException("Orden de Cobro no encontrada");
    }
    Collection collectionDb = this.findCollectionById(collectionOrderDb.getCollectionId());
    if (collectionDb == null) {
      log.warn("La coleccion con Id: {} no encontrada ", collectionOrderDb.getCollectionId());
      throw new NotFoundException("Cobro no encontrado");
    }
    try {
      JournalDTO journalDTO =
          JournalDTO.builder()
              .journalId("")
              .amount(collectionOrderDb.getAmount())
              .transactionReference(transactionDTO.getTransactionNumber())
              .transactionDate(new Date())
              .type("COLLECTION")
              .build();
      HttpEntity<JournalDTO> journalTransaction = new HttpEntity<>(journalDTO);
      JournalDTO journalCreated =
          this.restTemplate.postForObject(
              baseURLs.getCmAccountingJournalEntryURL(), journalTransaction, JournalDTO.class);
      collectionOrderDb.setJournalId(journalCreated.getJournalId());
      collectionDb.setPaidRecords(collectionDb.getPaidRecords() + 1);

      if(fullPaymentValue.equals(collectionOrderDb.getAmount())){
        collectionOrderDb.setState(CollectionOrderEnum.PAID.getValue());
        collectionOrderDb.setPaid(collectionOrderDb.getAmount());
      }else{
        collectionOrderDb.setPaid(collectionOrderDb.getPaid().add(fullPaymentValue));
        if((collectionOrderDb.getPaid().add(fullPaymentValue)).compareTo(collectionOrderDb.getAmount())==0){
          collectionOrderDb.setState(CollectionOrderEnum.PAID.getValue());
          collectionOrderDb.setPending(new BigDecimal(0));
        }else{
          collectionOrderDb.setPending(collectionOrderDb.getAmount().subtract(collectionOrderDb.getPaid()));
        }
      }

      this.collectionOrderRepository.save(collectionOrderDb);
      this.collectionRepository.save(collectionDb);
    } catch (Exception e) {
      collectionOrderDb.setState(CollectionOrderEnum.FAILED.getValue());
      collectionDb.setFailedRecords(collectionDb.getFailedRecords() + 1);
      collectionDb.setFailedValue(collectionDb.getFailedValue().add(fullPaymentValue));
      log.error("Error al realizar el pago");
      this.collectionOrderRepository.save(collectionOrderDb);
      this.collectionRepository.save(collectionDb);
    }
  }
}
