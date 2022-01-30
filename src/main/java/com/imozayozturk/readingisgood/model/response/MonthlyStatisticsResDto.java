package com.imozayozturk.readingisgood.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyStatisticsResDto {

  private String month;
  private int totalOrderCount;
  private int totalBookCount;
  private double totalPurchasedAmount;

}
