package com.banquito.scheduledcollections.dto.relational;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@Builder
public class TransactionDTO {

  private String creditorGroupInternalId;

  private BigInteger creditorAccountNumber;

  private String debtorGroupInternalId;

  private BigInteger debtorAccountNumber;

  private Date creationDate; // Required by frontend team

  private String serviceLevel;

  private BigDecimal amount;

  private String state;

  private String channel;

  private String externalOperation;

  private String reference;

  private String documentNumber;

  private String transactionNumber;
}
