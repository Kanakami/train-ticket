package com.example.orderGetTicketListByDateAndTripId.repository;

import com.example.orderGetTicketListByDateAndTripId.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    @Query("{ 'id': ?0 }")
    Order findById(UUID id);

    @Query("{ 'travelDate' : ?0 , trainNumber : ?1 }")
    ArrayList<Order> findByTravelDateAndTrainNumber(Date travelDate, String trainNumber);
}
