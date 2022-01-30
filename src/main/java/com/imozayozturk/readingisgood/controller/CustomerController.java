package com.imozayozturk.readingisgood.controller;

import com.imozayozturk.readingisgood.entity.Customer;
import com.imozayozturk.readingisgood.model.request.CustomerReqDto;
import com.imozayozturk.readingisgood.model.response.CustomerResDto;
import com.imozayozturk.readingisgood.service.CustomerService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customer")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public CustomerReqDto create(@RequestBody @Validated CustomerReqDto customerReqDto) {
    return customerService.createCustomer(customerReqDto).toBaseDto();
  }

  @GetMapping("{customerId}")
  public CustomerResDto getCustomer(@PathVariable("customerId") String customerId) {
    return customerService.getCustomer(customerId).toDto();
  }

  /**
   * A Rest endpoint for getting list of customers.
   *
   * @return List of customers.
   */
  @GetMapping
  public List<CustomerResDto> getCustomers() {
    return customerService.getCustomers()
        .stream().map(Customer::toDto)
        .collect(Collectors.toList());
  }

}