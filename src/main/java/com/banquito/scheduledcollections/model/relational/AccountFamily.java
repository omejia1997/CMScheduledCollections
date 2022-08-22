package com.banquito.scheduledcollections.model.relational;

import com.vladmihalcea.hibernate.type.array.StringArrayType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ACC_ACCOUNT_FAMILY")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@TypeDefs(@TypeDef(name = "string-array", typeClass = StringArrayType.class))
public class AccountFamily implements Serializable {

  private static final long serialVersionUID = -8742725422349733866L;

  @EmbeddedId @NonNull @EqualsAndHashCode.Include private AccountFamilyPK pk;

  @Column(name = "NAME", length = 128, nullable = false)
  private String name;

  @Column(name = "HOLDER_TYPE", length = 8, nullable = false)
  private String holderType;

  @Column(name = "RECOMMENDED_DEPOSIT_AMOUNT", precision = 22, scale = 6, nullable = false)
  private BigDecimal recommendedDepositAmount;

  @Column(name = "MAX_DEPOSIT_AMOUNT", precision = 22, scale = 6, nullable = false)
  private BigDecimal maxDepositAmount;

  @Column(name = "MAX_WITHDRAWAL_AMOUNT", precision = 22, scale = 6, nullable = false)
  private BigDecimal maxWithdrawalAmount;

  @Column(name = "MIN_WITHDRAWAL_AMOUNT", precision = 22, scale = 6, nullable = false)
  private BigDecimal minWithdrawalAmount;

  @Column(name = "ALLOWS_CHECKBOOK", length = 1, nullable = false)
  private String allowsCheckbook;

  @Column(name = "ALLOWS_OVERDRAFT", length = 1, nullable = false)
  private String allowsOverdraft;

  @Column(name = "PAYS_INTEREST", length = 1, nullable = false)
  private String paysInterest;

  @Type(type = "string-array")
  @Column(name = "CURRENCIES", columnDefinition = "text[]", length = 3, nullable = false)
  private String[] currencies;

  @Column(name = "STATE", length = 8, nullable = false)
  private String state;

  @Column(name = "MEMO", columnDefinition = "TEXT", nullable = false)
  private String memo;

  @Column(name = "CREATION_DATE", nullable = false)
  @Temporal(TemporalType.TIMESTAMP)
  private Date creationDate;

  @Column(name = "LAST_MODIFIED_DATE")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;

  @OneToMany(mappedBy = "accountFamily", fetch = FetchType.LAZY)
  private List<FeeSettings> fees;

  @OneToMany(mappedBy = "accountFamily", fetch = FetchType.LAZY)
  private List<InterestSettings> interests;

  @OneToMany(mappedBy = "accountFamily", fetch = FetchType.LAZY)
  private List<TaxSettings> taxes;
}
