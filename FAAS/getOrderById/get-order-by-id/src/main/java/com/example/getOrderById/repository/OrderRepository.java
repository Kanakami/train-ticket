package com.example.orderGetTicketListByDateAndTripId.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.example.orderGetTicketListByDateAndTripId.entity.Order;

import java.util.UUID;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ 'id': ?0 }")
    Order findById(UUID id);
}
