package com.banquito.scheduledcollections.model.relational;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class TaxSettingsPK implements Serializable {

  private static final long serialVersionUID = 18113429139980909L;

  @Column(name = "TAX_NAME", length = 10, nullable = false)
  @NonNull
  private String taxName;

  @Column(name = "ACCOUNT_TYPE_ID", length = 32, nullable = false)
  @NonNull
  private String typeId;

  @Column(name = "ACCOUNT_FAMILY_ID", length = 32, nullable = false)
  @NonNull
  private String familyId;
}
