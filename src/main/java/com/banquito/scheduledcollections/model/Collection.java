package com.banquito.scheduledcollections.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@Document(collection = "collections")
@TypeAlias("collections")
public class Collection {

  @Id private String id;

  @Indexed(name = "idx_collection_internalId", unique = true)
  private String internalId;

  private String collectionId;

  @Indexed(name = "idx_collection_groupInternalId", unique = false)
  private String groupInternalId;

  private BigInteger creditorAccount;

  private Date creationDate;

  private Date lastModifiedDate;

  private CollectionServiceOffered serviceOffered;

  private String state;

  private String periodicity;

  private String channel;

  private String reference;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date startCollectionDate;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private Date endCollectionDate;

  private Integer records;

  private Integer paidRecords;

  private Integer failedRecords;

  private BigDecimal totalCollectionValue;

  private BigDecimal failedValue;
}