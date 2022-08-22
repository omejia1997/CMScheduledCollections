package com.banquito.scheduledcollections.model.relational;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
public class AccountFamilyPK implements Serializable {

  private static final long serialVersionUID = -8742725422349733863L;

  @Column(name = "ACCOUNT_TYPE_ID", nullable = false, length = 32)
  @NonNull
  @EqualsAndHashCode.Include
  private String typeId;

  @Column(name = "ACCOUNT_FAMILY_ID", nullable = false, length = 32)
  @NonNull
  @EqualsAndHashCode.Include
  private String familyId;
}
