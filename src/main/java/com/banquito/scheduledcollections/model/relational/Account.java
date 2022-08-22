package com.banquito.scheduledcollections.model.relational;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "ACC_ACCOUNT")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Account implements Serializable {

  private static final long serialVersionUID = -167634057102358L;

  @EmbeddedId @EqualsAndHashCode.Include private AccountPK pk;

  @ManyToOne
  @JoinColumn(
      name = "ACCOUNT_TYPE_ID",
      referencedColumnName = "ACCOUNT_TYPE_ID",
      insertable = false,
      updatable = false)
  @JoinColumn(
      name = "ACCOUNT_FAMILY_ID",
      referencedColumnName = "ACCOUNT_FAMILY_ID",
      insertable = false,
      updatable = false)
  private AccountFamily accountFamily;

  @Column(name = "ACCOUNT_NUMBER", nullable = false)
  private BigInteger accountNumber;

  @Column(name = "CREATION_DATE", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Column(name = "OPENING_DATE", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date openingDate;

  @Column(name = "CLOSED_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date closedDate;

  @Column(name = "LAST_MODIFIED_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;

  @Column(name = "MATURITY_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date maturityDate;

  @Column(name = "BALANCE", precision = 22, scale = 6, nullable = false)
  private BigDecimal balance;

  @Column(name = "STATE", length = 8, nullable = false)
  private String state;
}
