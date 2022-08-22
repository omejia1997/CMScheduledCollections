package com.banquito.scheduledcollections.dto.relational;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {
  private BigInteger accountNumber;

  private String familyId;

  private String customerGroupId;

  private String accountType;

  private Date openingDate;

  private Date maturityDate;

  private BigDecimal balance;

  private String state;
}
