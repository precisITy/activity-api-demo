package com.precisity.demo.activity.api;

public class NotFoundException extends ApiException {

  private static final long serialVersionUID = -1079730820941591854L;

  public NotFoundException(String msg) {
    super(msg);
  }
}
