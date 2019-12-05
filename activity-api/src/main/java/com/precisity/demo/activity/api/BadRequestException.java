package com.precisity.demo.activity.api;

public class BadRequestException extends ApiException {

  private static final long serialVersionUID = 6839135952638479184L;

  public BadRequestException(String msg) {
    super(msg);
  }
}
