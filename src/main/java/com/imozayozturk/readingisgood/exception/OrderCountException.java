package com.imozayozturk.readingisgood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class OrderCountException extends RuntimeException {


  private static final long serialVersionUID = 8378665012300935860L;

  public OrderCountException(String message) {
    super(message);
  }

  public OrderCountException(String message, Throwable cause) {
    super(message, cause);
  }

}