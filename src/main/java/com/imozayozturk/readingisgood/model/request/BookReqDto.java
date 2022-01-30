package com.imozayozturk.readingisgood.model.request;

import com.imozayozturk.readingisgood.entity.Book;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
@AllArgsConstructor
public class BookReqDto {

  private String name;
  private String author;
  private Integer publishYear;
  private Integer stock;
  private BigDecimal amount;

  /**
   * Book object mappings from entity to transfer object.
   *
   * @param book Entity of book.
   * @return Transfer object of book.
   */
  public static BookReqDto fromEntity(Book book) {
    return BookReqDto
        .builder()
        .name(book.getName())
        .author(book.getAuthor())
        .publishYear(book.getPublishYear())
        .stock(book.getStock())
        .amount(book.getAmount())
        .build();
  }
}
