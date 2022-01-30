package com.imozayozturk.readingisgood;

import static org.mockito.Mockito.when;

import com.imozayozturk.readingisgood.controller.BookController;
import com.imozayozturk.readingisgood.entity.Book;
import com.imozayozturk.readingisgood.model.request.BookReqDto;
import com.imozayozturk.readingisgood.service.BookService;
import java.math.BigDecimal;
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
public class BookControllerTest {

  @InjectMocks
  private BookController mockBookController;

  @Mock
  private BookService mockBookService;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void createBookTest() {
    BookReqDto book = new BookReqDto(
        "Intibah",
        "Namık Kemal",
        1990,
        22,
        BigDecimal.valueOf(10L));

    when(mockBookService.createBook(ArgumentMatchers.any(BookReqDto.class)))
        .thenReturn(Book.createBook(book));

    BookReqDto foundBook = mockBookController.createBook(book);

    Assertions.assertThat(foundBook.getStock()).isEqualTo(22);
  }
}