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
public class FeeSettingsPK implements Serializable {

  private static final long serialVersionUID = 62272885648L;

  @Column(name = "FEE_NAME", length = 32, nullable = false)
  @NonNull
  private String feeName;

  @Column(name = "ACCOUNT_TYPE_ID", length = 32, nullable = false)
  @NonNull
  private String typeId;

  @Column(name = "ACCOUNT_FAMILY_ID", length = 32, nullable = false)
  @NonNull
  private String familyId;
}
