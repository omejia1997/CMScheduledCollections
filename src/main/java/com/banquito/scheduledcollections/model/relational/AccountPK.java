package com.banquito.scheduledcollections.model.relational;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountPK implements Serializable {

  private static final long serialVersionUID = 90137710745890L;

  @Column(name = "ACCOUNT_ID", nullable = false, length = 32)
  private String id;

  @Column(name = "ACCOUNT_TYPE_ID", nullable = false, length = 32)
  @NonNull
  private String typeId;

  @Column(name = "ACCOUNT_FAMILY_ID", nullable = false, length = 32)
  @NonNull
  private String familyId;

  @Column(name = "CUSTOMER_GROUP_ID", nullable = false, length = 50)
  @NonNull
  private String customerGroupId;
}
