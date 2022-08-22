package com.banquito.scheduledcollections.enums;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum SequenceEnum {
  EXTERNAL_OPERATION("External Operation", "EO"),
  DOCUMENT_NUMBER("Document Nummber", "DN"),
  TRANSACTION_NUMBER("Transaction Number", "TN");

  private final String id;
  private final String prefix;

  public String format(Long val) {
    return String.format("%s%07d", getPrefix(), val);
  }
}
