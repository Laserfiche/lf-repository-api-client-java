package com.laserfiche.repository.api.integration;

public enum ErrorSource {
  API_SERVER("Api Server"),
  LASERFICHE_SERVER("Laserfiche Server"),
  AUTHENTICATION_SERVICE("Authentication Services"),
  ACCOUNT_CONTROL_SYSTEM("Account Control System"),
  RWS("RWS");

  private String name;

  ErrorSource(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}
