package com.imozayozturk.readingisgood.model.response;

import com.imozayozturk.readingisgood.entity.Order;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class OrderResDto {

  private String id;
  private String customerId;
  private String bookId;
  private Integer orderCount;
  private LocalDateTime orderDate;


  /**
   * Order object mappings from entity to transfer object.
   *
   * @param order Entity of order.
   * @return Transfer object of order.
   */
  public static OrderResDto fromEntity(Order order) {
    return OrderResDto
        .builder()
        .customerId(order.getCustomerId())
        .bookId(order.getBookId())
        .orderDate(order.getOrderDate())
        .build();
  }
}
