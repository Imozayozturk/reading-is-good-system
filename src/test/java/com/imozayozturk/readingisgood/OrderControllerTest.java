package com.imozayozturk.readingisgood;

import static org.mockito.Mockito.when;

import com.imozayozturk.readingisgood.controller.OrderController;
import com.imozayozturk.readingisgood.entity.Order;
import com.imozayozturk.readingisgood.model.request.OrderReqDto;
import com.imozayozturk.readingisgood.model.response.OrderResDto;
import com.imozayozturk.readingisgood.service.OrderService;
import java.time.LocalDateTime;
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
public class OrderControllerTest {

  @InjectMocks
  private OrderController mockOrderController;

  @Mock
  private OrderService mockOrderService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createOrderTest() {
    OrderReqDto orderReqDto = new OrderReqDto(
        "655465",
        "123123",
        2);

    when(mockOrderService.createOrder(ArgumentMatchers.any(OrderReqDto.class)))
        .thenReturn(Order.createOrder(orderReqDto));

    OrderReqDto foundBook = mockOrderController.createOrder(orderReqDto);

    Assertions.assertThat(foundBook.getCustomerId()).isEqualTo("655465");
  }
}