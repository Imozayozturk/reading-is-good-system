package com.imozayozturk.readingisgood.repository;

import com.imozayozturk.readingisgood.entity.Order;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface OrderRepository extends MongoRepository<Order, String> {

  @Query("{'orderDate': {$gte: ?0, $lte:?1 }}")
  Optional<List<Order>> findOrdersByCreatedDateBetweenAndOrderByCreatedDate(Date startDate,
      Date endDate);

  @Query("{customerId : ?0}")
  Optional<List<Order>> findAllByCustomer(String customerId);
}
