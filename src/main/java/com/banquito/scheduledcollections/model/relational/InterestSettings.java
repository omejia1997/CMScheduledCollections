package com.banquito.scheduledcollections.model.relational;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "ACC_INTEREST_SETTINGS")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
public class InterestSettings implements Serializable {

  private static final long serialVersionUID = -5227755785744895856L;

  @EmbeddedId @NonNull @EqualsAndHashCode.Include private InterestSettingsPK pk;

  @Column(name = "CHARGE_FRECUENCY", length = 8, nullable = false)
  private String frecuency;

  @Column(name = "RATE", nullable = false, precision = 7, scale = 4)
  private BigDecimal rate;

  @Column(name = "RATE_TERMS", length = 8, nullable = false)
  private String rateTerms;

  @Column(name = "RATE_CALC_UNIT", length = 8, nullable = false)
  private String rateUnit;

  @Column(name = "SPREAD", nullable = false, precision = 7, scale = 4)
  private BigDecimal spread;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(
      name = "ACCOUNT_FAMILY_ID",
      referencedColumnName = "ACCOUNT_FAMILY_ID",
      nullable = false,
      insertable = false,
      updatable = false)
  @JoinColumn(
      name = "ACCOUNT_TYPE_ID",
      referencedColumnName = "ACCOUNT_TYPE_ID",
      nullable = false,
      insertable = false,
      updatable = false)
  private AccountFamily accountFamily;
}
