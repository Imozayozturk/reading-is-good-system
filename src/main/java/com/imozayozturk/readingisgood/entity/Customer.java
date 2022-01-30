package com.imozayozturk.readingisgood.entity;

import com.imozayozturk.readingisgood.model.request.CustomerReqDto;
import com.imozayozturk.readingisgood.model.response.CustomerResDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Customer")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Customer {

  private static final long serialVersionUID = -8974473478425482677L;
  @Id
  public String id;

  public String name;
  public String surname;
  @Indexed(unique = true)
  public String email;

  /**
   * Customer creation mappings.
   *
   * @param customerReqDto Transfer object of Customer.
   * @return Customer entity.
   */
  public static Customer createCustomer(CustomerReqDto customerReqDto) {
    return Customer.builder()
        .name(customerReqDto.getName())
        .surname(customerReqDto.getSurname())
        .email(customerReqDto.getEmail())
        .build();
  }

  public void updateCustomer(CustomerReqDto customerReqDto) {
    setName(customerReqDto.getName());
    setSurname(customerReqDto.getSurname());
  }

  /**
   * Customer request transfer object mappings.
   *
   * @return Request transfer object of Customer.
   */
  public CustomerReqDto toBaseDto() {
    return CustomerReqDto.builder()
        .name(getName())
        .surname(getSurname())
        .email(getEmail())
        .build();
  }

  /**
   * Customer response object mappings.
   *
   * @return Response transfer object of Customer.
   */
  public CustomerResDto toDto() {
    return CustomerResDto.builder()
        .id(getId())
        .name(getName())
        .surname(getSurname())
        .email(getEmail())
        .build();
  }
}
