package com.banquito.scheduledcollections.service;


import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.banquito.scheduledcollections.dao.CollectionOrderRepository;
import com.banquito.scheduledcollections.dao.CollectionRepository;
import com.banquito.scheduledcollections.enums.CollectionOrderEnum;
import com.banquito.scheduledcollections.enums.PaymentWayEnum;
import com.banquito.scheduledcollections.model.CollectionOrder;
import com.banquito.scheduledcollections.model.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollectionOrderService {
  private final CollectionOrderRepository collectionOrderRepository;
  private final CollectionRepository collectionRepository;

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

}
