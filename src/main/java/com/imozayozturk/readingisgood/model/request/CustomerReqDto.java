package com.imozayozturk.readingisgood.model.request;


import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class CustomerReqDto {


  @NotNull
  public String name;
  @NotNull
  public String surname;
  @NotNull
  public String email;
}
