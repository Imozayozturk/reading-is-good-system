package com.imozayozturk.readingisgood.model.request;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtRequest implements Serializable {


  private static final long serialVersionUID = 377310499033449564L;
  private String username;
  private String password;

  public JwtRequest() {

  }

  public JwtRequest(String username, String password) {
    this.setUsername(username);
    this.setPassword(password);
  }

}