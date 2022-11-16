package com.vf.uk.hack.backend.model;

public class BackendException extends RuntimeException {

  private final String code;

  public BackendException(final String code, final String message) {
    super(message);
    this.code = code;
  }

  public BackendException(final String code, final String message, final Throwable ex) {
    super(message, ex);
    this.code = code;
  }

  public String getCode() {
    return code;
  }
}