package com.example.travelGetTrainTypeByTripId.repository;

import com.example.travelGetTrainTypeByTripId.entity.Trip;
import com.example.travelGetTrainTypeByTripId.entity.TripId;
import org.springframework.data.repository.CrudRepository;


import java.util.ArrayList;

/**
 * @author fdse
 */
public interface TripRepository extends CrudRepository<Trip, TripId> {

    Trip findByTripId(TripId tripId);

    void deleteByTripId(TripId tripId);

    @Override
    ArrayList<Trip> findAll();

    ArrayList<Trip> findByRouteId(String routeId);
}
