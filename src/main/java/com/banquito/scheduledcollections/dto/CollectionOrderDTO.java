package com.banquito.scheduledcollections.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import lombok.Data;

@Data
public class CollectionOrderDTO {

  private String collectionId;

  private String referenceId;

  private String internalId;

  private CollectionCustomerDTO customer;

  private BigInteger debtorAccount;

  private String paymentWay;

  private BigDecimal amount;

  private BigDecimal paid;

  private BigDecimal pending;

  private String reference;

  private String processingState;

  private String state;

  private Date collectedDate;

  private Date startCollectionDate;

  private Date endCollectionDate;

  private String journalId;
}
