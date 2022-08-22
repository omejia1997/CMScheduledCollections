package com.banquito.scheduledcollections.dto;

import java.math.BigDecimal;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JournalDTO {

  private String transactionReference;

  private String journalId;

  private BigDecimal amount;

  private String type;

  private Date transactionDate;

  private String description;
}
