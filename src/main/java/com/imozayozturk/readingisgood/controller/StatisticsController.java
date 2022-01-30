package com.imozayozturk.readingisgood.controller;

import com.imozayozturk.readingisgood.model.response.StatisticsResDto;
import com.imozayozturk.readingisgood.service.StatisticsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("statistics")
public class StatisticsController {

  @Autowired
  private StatisticsService statisticsService;


  @GetMapping()
  public StatisticsResDto getStatistics(@RequestParam("customerId") String customerId) {
    return statisticsService.getStatisticsByCustomerId(customerId);
  }

}