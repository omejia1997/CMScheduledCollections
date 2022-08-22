package com.banquito.scheduledcollections.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CollectionServiceOffered {

  private String name;

  private String description;
}
