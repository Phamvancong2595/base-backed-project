package com.congpv.baseproject.infrastructure.shared.exception;

public class EmptyRequestException extends BaseException {

  public EmptyRequestException(String msg) {
    super(msg);
  }

  public EmptyRequestException() {
    super();
  }

}
