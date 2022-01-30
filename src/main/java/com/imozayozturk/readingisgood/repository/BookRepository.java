package com.imozayozturk.readingisgood.repository;

import com.imozayozturk.readingisgood.entity.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {

}
