package com.banquito.scheduledcollections.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "db_sequences")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sequence {

  @Id private String id;
  private Long value;
}
