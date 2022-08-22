package com.banquito.scheduledcollections.dao;

import com.banquito.scheduledcollections.model.CollectionOrder;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CollectionOrderRepository extends MongoRepository<CollectionOrder, String> {

  List<CollectionOrder> findByStateAndPaymentWayAndStartCollectionDateGreaterThanAndEndCollectionDateLessThan(
      String state, String paymentWay, Date startCollectionDate, Date endCollectionDate);

  List<CollectionOrder> findByStateOrderByCollectionId(
      String state);
}
