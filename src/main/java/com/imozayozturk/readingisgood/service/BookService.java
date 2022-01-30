package com.imozayozturk.readingisgood.service;

import com.imozayozturk.readingisgood.entity.Book;
import com.imozayozturk.readingisgood.model.request.BookReqDto;
import com.imozayozturk.readingisgood.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

  @Autowired
  private BookRepository bookRepository;

  public Book createBook(BookReqDto bookReqDto) {
    return bookRepository.save(Book.createBook(bookReqDto));
  }

  public List<Book> getBooks() {
    return bookRepository.findAll();
  }

  public Book getBookById(String bookId) {
    return bookRepository.findById(bookId).orElse(null);
  }

  /**
   * Book stock update business method.
   *
   * @param bookId Identification of book.
   * @param stockCount The remain count of stock
   * @return Book entity.
   */
  public Book updateBookStock(String bookId, Integer stockCount) {
    bookRepository.findById(bookId)
        .ifPresentOrElse(
            book -> {
              book.updateStockCount(stockCount);
              bookRepository.save(book);
            },
            () -> {
              throw new RuntimeException("Book not found");
            }
        );
    log.info("Book stock has been updated: bookId:{}, count:{},",
        bookId, stockCount);
    return bookRepository.findById(bookId).orElse(null);
  }
}
