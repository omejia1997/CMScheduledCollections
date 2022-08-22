package com.banquito.scheduledcollections.enums;

public enum CollectionOrderEnum {
  TOPAY("TP", "ToPay"),
  PAID("PP", "Paid"),
  FAILED("FF", "Failed"),
  READY("RD", "Ready");

  private final String value;
  private final String text;

  private CollectionOrderEnum(String value, String text) {
    this.value = value;
    this.text = text;
  }

  public String getText() {
    return this.text;
  }

  public String getValue() {
    return this.value;
  }
}
