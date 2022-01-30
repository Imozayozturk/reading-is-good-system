package com.imozayozturk.readingisgood.entity;

import com.imozayozturk.readingisgood.model.request.BookReqDto;
import com.imozayozturk.readingisgood.model.response.BookResDto;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Book")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Book {

  private static final long serialVersionUID = -3640922192082169178L;
  @Id
  public String id;

  private String name;
  private String author;
  private Integer publishYear;
  private Integer stock;
  private BigDecimal amount;


  /**
   * Book creation mappings.
   *
   * @param bookReqDto Transfer object of book.
   * @return Book entity.
   */
  public static Book createBook(BookReqDto bookReqDto) {
    return Book.builder()
        .name(bookReqDto.getName())
        .author(bookReqDto.getAuthor())
        .publishYear(bookReqDto.getPublishYear())
        .stock(bookReqDto.getStock())
        .amount(bookReqDto.getAmount())
        .build();
  }

  public void updateStockCount(Integer stockCount) {
    setStock(stockCount);
  }
}
