package com.imozayozturk.readingisgood.entity;

import com.imozayozturk.readingisgood.model.request.OrderReqDto;
import com.imozayozturk.readingisgood.model.response.OrderResDto;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Order {

  private static final long serialVersionUID = 1315737464722032464L;
  @Id
  public String id;

  private String customerId;
  private String bookId;
  private Integer orderCount;
  private LocalDateTime orderDate;
  @Version
  private Integer version;

  /**
   * Order creation mappings.
   *
   * @param orderReqDto Transfer object of order.
   * @return Order entity.
   */
  public static Order createOrder(OrderReqDto orderReqDto) {
    return Order.builder()
        .customerId(orderReqDto.getCustomerId())
        .bookId(orderReqDto.getBookId())
        .orderCount(orderReqDto.getOrderCount())
        .orderDate(LocalDateTime.now())
        .build();
  }

  /**
   * Order request transfer object mappings.
   *
   * @return Request transfer object of order.
   */
  public OrderReqDto toBaseDto() {
    return OrderReqDto.builder()
        .customerId(getCustomerId())
        .bookId(getBookId())
        .orderCount(getOrderCount())
        .build();
  }

  /**
   * Order response transfer object mappings.
   *
   * @return Response transfer object of order.
   */
  public OrderResDto toDto() {
    return OrderResDto.builder()
        .id(getId())
        .customerId(getCustomerId())
        .bookId(getBookId())
        .orderCount(getOrderCount())
        .orderDate(getOrderDate())
        .build();
  }
}
