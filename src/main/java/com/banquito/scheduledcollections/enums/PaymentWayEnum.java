package com.banquito.scheduledcollections.enums;

public enum PaymentWayEnum {
  MANUAL("MAN", "Manual"),
  RECURRENT("REC", "Recurrent");

  private final String value;
  private final String text;

  private PaymentWayEnum(String value, String text) {
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
