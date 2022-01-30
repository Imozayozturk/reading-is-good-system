package com.imozayozturk.readingisgood.service;

import com.imozayozturk.readingisgood.entity.Book;
import com.imozayozturk.readingisgood.entity.Order;
import com.imozayozturk.readingisgood.model.response.MonthlyStatisticsResDto;
import com.imozayozturk.readingisgood.model.response.StatisticsResDto;
import com.imozayozturk.readingisgood.repository.BookRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StatisticsService {

  @Autowired
  private OrderService orderService;

  @Autowired
  private BookRepository bookRepository;

  /**
   * Statistics by customer.
   *
   * @param customerId Identification of customer.
   * @return Statistics entity.
   */
  public StatisticsResDto getStatisticsByCustomerId(String customerId) {

    List<Order> ordersByCustomerId = orderService.getOrdersByCustomerId(customerId);

    Map<String, List<Order>> monthlyMappedOrders = ordersByCustomerId.stream()
        .collect(Collectors.groupingBy(item -> item.getOrderDate().getMonth().toString()));

    StatisticsResDto statistics = new StatisticsResDto();
    List<MonthlyStatisticsResDto> monthlyStatistics = new ArrayList<>();

    monthlyMappedOrders.entrySet()
        .forEach(item -> monthlyStatistics.add(getStatisticsOfMonth(item)));
    statistics.setStatistics(monthlyStatistics);
    return statistics;
  }


  private MonthlyStatisticsResDto getStatisticsOfMonth(Entry<String, List<Order>> entry) {
    MonthlyStatisticsResDto monthlyStatisticsDto = new MonthlyStatisticsResDto();
    monthlyStatisticsDto.setMonth(entry.getKey());
    List<Order> ordersInMonth = entry.getValue();
    List<Book> bookList = bookRepository.findAll();

    double totalPrice = 0;
    int totalBookCount = 0;
    int totalOrderCount = ordersInMonth.size();
    for (Order order : ordersInMonth) {
      int count = order.getOrderCount();
      totalBookCount += count;
      Book book = bookList.stream().filter(item -> item.getId().equals(order.getBookId()))
          .findAny().orElse(null);
      if (ObjectUtils.isNotEmpty(book) && ObjectUtils.isNotEmpty(book.getAmount())) {
        totalPrice += count * book.getAmount().intValue();
      }
    }

    monthlyStatisticsDto.setTotalOrderCount(totalOrderCount);
    monthlyStatisticsDto.setTotalBookCount(totalBookCount);
    monthlyStatisticsDto.setTotalPurchasedAmount(totalPrice);
    return monthlyStatisticsDto;
  }
}
