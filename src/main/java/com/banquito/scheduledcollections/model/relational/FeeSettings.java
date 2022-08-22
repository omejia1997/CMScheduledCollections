package com.banquito.scheduledcollections.model.relational;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "ACC_FEE_SETTINGS")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
public class FeeSettings implements Serializable {

  private static final long serialVersionUID = 2651941551628L;

  @EmbeddedId @NonNull @EqualsAndHashCode.Include private FeeSettingsPK pk;

  @Column(name = "CALCULATION_TYPE", length = 8, nullable = false)
  private String calculationType;

  @Column(name = "APPLICATION_FRECUENCY", length = 10, nullable = false)
  private String applicationFrecuency;

  @Column(name = "AMOUNT", precision = 22, scale = 6, nullable = false)
  private BigDecimal amount;

  @Column(name = "TRIGGER", length = 10, nullable = false)
  private String trigger;

  @Column(name = "APPLICATION_START_DATE", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date applicationStartDate;

  @Column(name = "CONDITION", length = 256, nullable = false)
  private String condition;

  @Column(name = "FORMULA", length = 256, nullable = false)
  private String formula;

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
