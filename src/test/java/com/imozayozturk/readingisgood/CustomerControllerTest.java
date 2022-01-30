package com.imozayozturk.readingisgood;

import static org.mockito.Mockito.when;

import com.imozayozturk.readingisgood.controller.CustomerController;
import com.imozayozturk.readingisgood.entity.Customer;
import com.imozayozturk.readingisgood.model.request.CustomerReqDto;
import com.imozayozturk.readingisgood.service.CustomerService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerControllerTest {

  @InjectMocks
  private CustomerController mockCustomerController;

  @Mock
  private CustomerService mockCustomerService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createCustomerTest() {
    CustomerReqDto customerReqDto = new CustomerReqDto(
        "Ozay",
        "Ozturk",
        "example@test.mail.com");

    when(mockCustomerService.createCustomer(ArgumentMatchers.any(CustomerReqDto.class)))
        .thenReturn(Customer.createCustomer(customerReqDto));

    CustomerReqDto foundCustomer = mockCustomerController.create(customerReqDto);

    Assertions.assertThat(foundCustomer.getName()).isEqualTo("Ozay");
  }
}