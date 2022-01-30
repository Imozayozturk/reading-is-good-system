package com.imozayozturk.readingisgood.controller;

import com.imozayozturk.readingisgood.entity.Order;
import com.imozayozturk.readingisgood.model.request.OrderReqDto;
import com.imozayozturk.readingisgood.model.response.OrderResDto;
import com.imozayozturk.readingisgood.service.OrderService;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class OrderController {

  @Autowired
  private OrderService orderService;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public OrderReqDto createOrder(@RequestBody @Validated OrderReqDto orderReqDto) {
    return orderService.createOrder(orderReqDto).toBaseDto();
  }

  @GetMapping("{orderId}")
  @ResponseStatus(HttpStatus.OK)
  public OrderResDto getOrder(@PathVariable("orderId") String orderId) {
    return orderService.getOrder(orderId).toDto();
  }


  //• Will query all orders of the customer(Paging sounds really nice) -
  // I think this should a part of orderController, so it has been implemented on here.
  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  public List<OrderResDto> filter(@RequestParam("customerId") String customerId) {
    return orderService.getOrdersByCustomerId(customerId).stream().map(Order::toDto)
        .collect(Collectors.toList());
  }

  @GetMapping("betweenDates")
  @ResponseStatus(HttpStatus.OK)
  public List<OrderResDto> filter(@RequestParam("startDate") Long startDate,
      @RequestParam("endDate") Long endDate) {
    return orderService.getOrdersBetweenDates(new Date(startDate), new Date(endDate)).stream()
        .map(Order::toDto).collect(Collectors.toList());
  }

}