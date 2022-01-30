package com.imozayozturk.readingisgood.model.response;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;


@Data
@Builder
@ToString
@AllArgsConstructor
public class CustomerResDto {

  private String id;
  @NotNull
  public String name;
  @NotNull
  public String surname;
  @NotNull
  public String email;
}
