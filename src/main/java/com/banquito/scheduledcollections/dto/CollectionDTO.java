package com.banquito.scheduledcollections.dto;

import java.math.BigInteger;
import java.util.Date;
import lombok.Data;

@Data
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
}
