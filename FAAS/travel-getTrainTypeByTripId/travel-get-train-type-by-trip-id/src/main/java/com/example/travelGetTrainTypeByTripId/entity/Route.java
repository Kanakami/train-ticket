package com.example.travelGetTrainTypeByTripId.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author fdse
 */
@Data
@AllArgsConstructor
@Document(collection = "routes")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Route {

    @Id
    private String id;

    private List<String> stations;

    private List<Integer> distances;

    private String startStationId;

    private String terminalStationId;

    public Route() {
        //Default Constructor
    }
}
