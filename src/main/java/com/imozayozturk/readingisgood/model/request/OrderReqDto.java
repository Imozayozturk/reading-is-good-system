package com.imozayozturk.readingisgood.model.request;

import com.imozayozturk.readingisgood.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class OrderReqDto {

  private String customerId;
  private String bookId;
  private Integer orderCount;


  /**
   * Order object mappings from entity to transfer object.
   *
   * @param order Entity of order.
   * @return Transfer object of order.
   */
  public static OrderReqDto fromEntity(Order order) {
    return OrderReqDto
        .builder()
        .customerId(order.getCustomerId())
        .bookId(order.getBookId())
        .orderCount(order.getOrderCount())
        .build();
  }
}
