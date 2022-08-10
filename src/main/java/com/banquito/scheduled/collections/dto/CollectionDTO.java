package com.banquito.scheduled.collections.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionDTO {

  private String internalId;

  private String groupInternalId;

  private BigInteger creditorAccount;

  private Date creationDate;

  private CollectionServiceOfferedDTO serviceOffered;

  private String state;

  private String periodicity;

  private String channel;

  private String reference;

  private Date startCollectionDate;

  private Date endCollectionDate;

  private Integer records;

  private Integer paidRecords;

  private Integer failedRecords;

  private BigDecimal totalCollectionValue;

  private BigDecimal failedValue;
}
