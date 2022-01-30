package com.imozayozturk.readingisgood.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StockException extends RuntimeException {

  private static final long serialVersionUID = -101886023698563414L;

  public StockException(String message) {
    super(message);
  }

  public StockException(String message, Throwable cause) {
    super(message, cause);
  }

}