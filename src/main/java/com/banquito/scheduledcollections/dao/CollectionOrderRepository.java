package com.banquito.scheduledcollections.dao;

import com.banquito.scheduledcollections.model.CollectionOrder;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollectionOrderRepository extends MongoRepository<CollectionOrder, String> {

  List<CollectionOrder> findByStateAndPaymentWayAndStartCollectionDateGreaterThanAndEndCollectionDateLessThan(
      String state, String paymentWay, Date startCollectionDate, Date endCollectionDate);

  List<CollectionOrder> findByStateAndPaymentWay(
          String state, String paymentWay);

  List<CollectionOrder> findByStateOrderByCollectionId(
      String state);

  Optional<CollectionOrder> findByInternalId(String internalId);
}
