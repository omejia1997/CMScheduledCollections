package com.banquito.scheduledcollections.model;

import com.mongodb.lang.Nullable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "collection_orders")
@TypeAlias("collection_orders")
public class CollectionOrder {

  @Id private String id;

  @Indexed(name = "idx_collectionOrder_internalId", unique = true)
  private String internalId;

  @Indexed(name = "idx_collectionOrder_referenceId", unique = false)
  private String referenceId;

  private String collectionId;

  @Indexed(name = "idx_collectionOrder_debtorAccount", unique = false)
  @Nullable
  private BigInteger debtorAccount;

  private String paymentWay;

  @Indexed(name = "idx_collectionOrder_customer", unique = false)
  private CollectionCustomer customer;

  private BigDecimal amount;

  private BigDecimal paid;

  private BigDecimal pending;

  private String processingState;

  private String state;

  private String reference;

  private Date collectedDate;

  private Date startCollectionDate;

  private Date endCollectionDate;

  private String journalId;
}
