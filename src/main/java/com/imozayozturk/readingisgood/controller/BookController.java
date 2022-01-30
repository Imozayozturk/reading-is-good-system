package com.imozayozturk.readingisgood.controller;

import com.imozayozturk.readingisgood.model.request.BookReqDto;
import com.imozayozturk.readingisgood.model.response.BookResDto;
import com.imozayozturk.readingisgood.service.BookService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("book")
@RequiredArgsConstructor
public class BookController {

  @Autowired
  private BookService bookService;

  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public BookReqDto createBook(@RequestBody @Validated BookReqDto bookReqDto) {
    return BookReqDto.fromEntity(bookService.createBook(bookReqDto));
  }

  @PutMapping("{bookId}")
  public BookResDto updateBookStock(@PathVariable("bookId") String bookId,
      @RequestParam("stockCount") Integer stockCount) {
    return BookResDto.fromEntity(bookService.updateBookStock(bookId, stockCount));
  }

  /**
   * A Rest endpoint for getting list of books.
   *
   * @return List of books.
   */
  @GetMapping()
  public List<BookResDto> getBooks() {
    return bookService.getBooks()
        .stream().map(BookResDto::fromEntity)
        .collect(Collectors.toList());
  }
}