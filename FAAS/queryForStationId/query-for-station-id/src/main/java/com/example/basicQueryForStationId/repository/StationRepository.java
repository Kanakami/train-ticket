package com.example.basicQueryForStationId.repository;

import com.example.basicQueryForStationId.entity.Station;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface StationRepository extends CrudRepository<Station,String> {

    Station findByName(String name);

    // 解决返回为空的情况
    Optional<Station> findById(String id);

    @Override
    List<Station> findAll();
}
