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
@Table(name = "ACC_TAX_SETTINGS")
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
public class TaxSettings implements Serializable {

  private static final long serialVersionUID = 785744895856L;

  @EmbeddedId @NonNull @EqualsAndHashCode.Include private TaxSettingsPK pk;

  @Column(name = "RATE", precision = 7, scale = 4, nullable = false)
  private BigDecimal rate;

  @Column(name = "APPLICATION_FRECUENCY", length = 10, nullable = false)
  private String applicationFrecuency;

  @Column(name = "TRIGGER", length = 10, nullable = false)
  private String trigger;

  @Column(name = "FORMULA", length = 255, nullable = false)
  private String formula;

  @Column(name = "APPLICATION_START_DATE", nullable = false)
  @Temporal(TemporalType.DATE)
  private Date applicationStartDate;

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
