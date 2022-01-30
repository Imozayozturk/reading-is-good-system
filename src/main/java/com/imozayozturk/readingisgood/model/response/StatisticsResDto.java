package com.imozayozturk.readingisgood.model.response;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class StatisticsResDto {

  private List<MonthlyStatisticsResDto> statistics;
}
