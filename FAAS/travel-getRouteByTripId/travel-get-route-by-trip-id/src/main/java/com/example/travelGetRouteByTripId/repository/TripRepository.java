package com.example.travelGetRouteByTripId.repository;

import org.springframework.data.repository.CrudRepository;
import com.example.travelGetRouteByTripId.entity.Trip;
import com.example.travelGetRouteByTripId.entity.TripId;

import java.util.ArrayList;

/**
 * @author fdse
 */
public interface TripRepository extends CrudRepository<Trip,TripId> {

    Trip findByTripId(TripId tripId);

    void deleteByTripId(TripId tripId);

    @Override
    ArrayList<Trip> findAll();

    ArrayList<Trip> findByRouteId(String routeId);
}
