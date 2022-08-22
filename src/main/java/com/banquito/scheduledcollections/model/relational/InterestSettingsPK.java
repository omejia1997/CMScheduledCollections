package com.banquito.scheduledcollections.model.relational;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
@AllArgsConstructor
public class InterestSettingsPK implements Serializable {

  private static final long serialVersionUID = -9118113429139980909L;

  @Column(name = "INTEREST_SETTINGS_TYPE", length = 10, nullable = false)
  @NonNull
  @EqualsAndHashCode.Include
  private String id;

  @Column(name = "ACCOUNT_TYPE_ID", length = 32, nullable = false)
  @NonNull
  @EqualsAndHashCode.Include
  private String typeId;

  @Column(name = "ACCOUNT_FAMILY_ID", length = 32, nullable = false)
  @NonNull
  @EqualsAndHashCode.Include
  private String familyId;
}
