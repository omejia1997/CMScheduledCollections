package com.banquito.scheduledcollections.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionCustomer {

  private String customerId;

  private String typeCustomerId;

  private String fullName;
}
