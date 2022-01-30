package com.imozayozturk.readingisgood.service;

import com.imozayozturk.readingisgood.entity.Customer;
import com.imozayozturk.readingisgood.model.request.CustomerReqDto;
import com.imozayozturk.readingisgood.repository.CustomerRepository;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public Customer getCustomer(String customerId) {
    return customerRepository.findById(customerId)
        .orElseThrow(() -> new RuntimeException("Customer not found"));
  }

  public List<Customer> getCustomers() {
    return customerRepository.findAll();
  }

  public Customer createCustomer(CustomerReqDto customerReqDto) {
    return customerRepository.save(Customer.createCustomer(customerReqDto));
  }

}
