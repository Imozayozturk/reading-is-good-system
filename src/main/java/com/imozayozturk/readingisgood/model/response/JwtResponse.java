package com.imozayozturk.readingisgood.model.response;

import java.io.Serializable;

public class JwtResponse implements Serializable {

  private static final long serialVersionUID = -3220982230601040734L;
  private final String jwttoken;

  public JwtResponse(String jwttoken) {
    this.jwttoken = jwttoken;
  }

  public String getToken() {
    return this.jwttoken;
  }
}