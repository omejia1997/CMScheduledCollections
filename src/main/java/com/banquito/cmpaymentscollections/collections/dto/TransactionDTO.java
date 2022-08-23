package com.banquito.cmpaymentscollections.collections.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {

  private String creditorGroupInternalId;

  private BigInteger creditorAccountNumber;

  private String debtorGroupInternalId;

  private BigInteger debtorAccountNumber;

  private String serviceLevel;

  private BigDecimal amount;

  private String state;

  private String channel;

  private String externalOperation;

  private String reference;

  private String documentNumber;

  private String transactionNumber;
}

