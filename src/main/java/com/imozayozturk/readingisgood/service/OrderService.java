package com.imozayozturk.readingisgood.service;

import com.imozayozturk.readingisgood.entity.Book;
import com.imozayozturk.readingisgood.entity.Order;
import com.imozayozturk.readingisgood.exception.OrderCountException;
import com.imozayozturk.readingisgood.exception.StockException;
import com.imozayozturk.readingisgood.model.request.OrderReqDto;
import com.imozayozturk.readingisgood.repository.OrderRepository;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private BookService bookService;

  /**
   * Order creation.
   *
   * @param orderReqDto Request transfer object for order.
   * @return Order entity.
   */
  @Transactional
  public Order createOrder(OrderReqDto orderReqDto) {

    checkValidationBeforeOrder(orderReqDto);

    return orderRepository.save(Order.createOrder(orderReqDto));
  }

  private void checkValidationBeforeOrder(OrderReqDto orderReqDto) {
    if (orderReqDto.getOrderCount() <= 0) {
      log.error("Need valid order count for request: customerId:{}, bookId:{}, count:{},",
          orderReqDto.getCustomerId(), orderReqDto.getBookId(), orderReqDto.getOrderCount());
      throw new OrderCountException("Need valid order count");
    }

    Book book = bookService.getBookById(orderReqDto.getBookId());

    if (ObjectUtils.isEmpty(book)) {
      throw new RuntimeException("Book could not find");
    }

    if (ObjectUtils.isNotEmpty(book) && (book.getStock() < orderReqDto.getOrderCount())) {
      log.error("UnsufficientStock: customerId:{}, bookId:{}, count:{}, stock{}",
          orderReqDto.getCustomerId(), orderReqDto.getBookId(), orderReqDto.getOrderCount(),
          book.getStock());
      throw new StockException("Unsufficient stock count");
    }
  }

  public Order getOrder(String orderId) {
    return orderRepository.findById(orderId)
        .orElseThrow(() -> new RuntimeException("Order not found"));
  }

  public List<Order> getOrdersByCustomerId(String customerId) {
    return orderRepository.findAllByCustomer(customerId).orElse(List.of());
  }

  public List<Order> getOrdersBetweenDates(Date startDate, Date endDate) {
    return orderRepository.findOrdersByCreatedDateBetweenAndOrderByCreatedDate(startDate, endDate)
        .orElse(List.of());
  }
}
