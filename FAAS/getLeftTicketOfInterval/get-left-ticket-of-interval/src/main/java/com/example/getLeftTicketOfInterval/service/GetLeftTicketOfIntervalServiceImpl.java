package com.example.getLeftTicketOfInterval.service;

import com.example.getLeftTicketOfInterval.entity.*;
import edu.fudan.common.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.example.queryRouteById.entity.*;

import java.util.List;
import java.util.Set;

@Service
public class GetLeftTicketOfIntervalServiceImpl implements GetLeftTicketOfIntervalService {
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(GetLeftTicketOfIntervalServiceImpl.class);

    @Override
    public Response getLeftTicketOfInterval(Seat seatRequest, HttpHeaders headers) {
        int numOfLeftTicket = 0;
        Response<Route> routeResult;
        TrainType trainTypeResult;
        LeftTicketInfo leftTicketInfo;

        ResponseEntity<Response<Route>> re;
        ResponseEntity<Response<TrainType>> re2;
        ResponseEntity<Response<LeftTicketInfo>> re3;

        //Distinguish G\D from other trains
        String trainNumber = seatRequest.getTrainNumber();
        GetLeftTicketOfIntervalServiceImpl.LOGGER.info("Seat request To String: {}", seatRequest.toString());
        if (trainNumber.startsWith("G") || trainNumber.startsWith("D")) {
            GetLeftTicketOfIntervalServiceImpl.LOGGER.info("[SeatService getLeftTicketOfInterval] TrainNumber start with G|D {}", trainNumber);

            //Call the micro service to query all the station information for the trains
            HttpEntity requestEntity = new HttpEntity(headers);
            re = restTemplate.exchange(
//                    "http://ts-travel-service:12346/api/v1/travelservice/routes/" + trainNumber,
                    "http://10.176.122.15:31112/function/travel-get-route-by-trip-id/routes/" + trainNumber,
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<Response<Route>>() {
                    });
            routeResult = re.getBody();
            GetLeftTicketOfIntervalServiceImpl.LOGGER.info("[SeatService getLeftTicketOfInterval] The result of getRouteResult is {}", routeResult.getMsg());

            //Call the micro service to query for residual Ticket information: the set of the Ticket sold for the specified seat type
            requestEntity = new HttpEntity(seatRequest, headers);
            re3 = restTemplate.exchange(
//                    "http://ts-order-service:12031/api/v1/orderservice/order/tickets",
                    "http://10.176.122.15:31112/function/order-get-ticket-list-by-date-and-trip-id/order/tickets",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<Response<LeftTicketInfo>>() {
                    });

            GetLeftTicketOfIntervalServiceImpl.LOGGER.info("Get Order tickets result is : {}", re3);
            leftTicketInfo = re3.getBody().getData();

            //Calls the microservice to query the total number of seats specified for that vehicle
            requestEntity = new HttpEntity(headers);
            re2 = restTemplate.exchange(
//                    "http://ts-travel-service:12346/api/v1/travelservice/train_types/" + seatRequest.getTrainNumber(),
                    "http://10.176.122.15:31112/function/travel-get-train-type-by-trip-id/train_types/" + seatRequest.getTrainNumber(),
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<Response<TrainType>>() {
                    });
            Response<TrainType> trainTypeResponse = re2.getBody();


            trainTypeResult = trainTypeResponse.getData();
            GetLeftTicketOfIntervalServiceImpl.LOGGER.info("[SeatService getLeftTicketOfInterval] The result of getTrainTypeResult is {}", trainTypeResponse.toString());
        } else {
            GetLeftTicketOfIntervalServiceImpl.LOGGER.info("[SeatService getLeftTicketOfInterval] TrainNumber start with other capital");
            //Call the micro service to query all the station information for the trains
            HttpEntity requestEntity = new HttpEntity(headers);
            re = restTemplate.exchange(
                    "http://ts-travel2-service:16346/api/v1/travel2service/routes/" + seatRequest.getTrainNumber(),
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<Response<Route>>() {
                    });
            routeResult = re.getBody();
            GetLeftTicketOfIntervalServiceImpl.LOGGER.info("[SeatService getLeftTicketOfInterval] The result of getRouteResult is {}", routeResult.toString());

            //Call the micro service to query for residual Ticket information: the set of the Ticket sold for the specified seat type
            requestEntity = new HttpEntity(seatRequest, headers);
            re3 = restTemplate.exchange(
                    "http://ts-order-other-service:12032/api/v1/orderOtherService/orderOther/tickets",
                    HttpMethod.POST,
                    requestEntity,
                    new ParameterizedTypeReference<Response<LeftTicketInfo>>() {
                    });
            GetLeftTicketOfIntervalServiceImpl.LOGGER.info("Get Order tickets result is : {}", re3);
            leftTicketInfo = re3.getBody().getData();


            //Calls the microservice to query the total number of seats specified for that vehicle
            requestEntity = new HttpEntity(headers);
            re2 = restTemplate.exchange(
                    "http://ts-travel2-service:16346/api/v1/travel2service/train_types/" + seatRequest.getTrainNumber(),
                    HttpMethod.GET,
                    requestEntity,
                    new ParameterizedTypeReference<Response<TrainType>>() {
                    });
            Response<TrainType> trainTypeResponse = re2.getBody();
            trainTypeResult = trainTypeResponse.getData();
            GetLeftTicketOfIntervalServiceImpl.LOGGER.info("[SeatService getLeftTicketOfInterval] The result of getTrainTypeResult is {}", trainTypeResponse.toString());
        }

        //Counting the seats remaining in certain sections
        List<String> stationList = routeResult.getData().getStations();
        int seatTotalNum;
        if (seatRequest.getSeatType() == SeatClass.FIRSTCLASS.getCode()) {
            seatTotalNum = trainTypeResult.getConfortClass();
            GetLeftTicketOfIntervalServiceImpl.LOGGER.info("[SeatService getLeftTicketOfInterval] The request seat type is confortClass and the total num is {}", seatTotalNum);
        } else {
            seatTotalNum = trainTypeResult.getEconomyClass();
            GetLeftTicketOfIntervalServiceImpl.LOGGER.info("[SeatService getLeftTicketOfInterval] The request seat type is economyClass and the total num is {}", seatTotalNum);
        }

        int solidTicketSize = 0;
        if (leftTicketInfo != null) {
            String startStation = seatRequest.getStartStation();
            Set<Ticket> soldTickets = leftTicketInfo.getSoldTickets();
            solidTicketSize = soldTickets.size();
            //To find out if tickets already sold are available
            for (Ticket soldTicket : soldTickets) {
                String soldTicketDestStation = soldTicket.getDestStation();
                //Tickets can be allocated if the sold ticket's end station before the start station of the request
                if (stationList.indexOf(soldTicketDestStation) < stationList.indexOf(startStation)) {
                    GetLeftTicketOfIntervalServiceImpl.LOGGER.info("[SeatService getLeftTicketOfInterval] The previous distributed seat number is usable! {}", soldTicket.getSeatNo());
                    numOfLeftTicket++;
                }
            }
        }
        //Count the unsold tickets

        double direstPart = getDirectProportion(headers);
        Route route = routeResult.getData();
        if (route.getStations().get(0).equals(seatRequest.getStartStation()) &&
                route.getStations().get(route.getStations().size() - 1).equals(seatRequest.getDestStation())) {
            //do nothing
        } else {
            direstPart = 1.0 - direstPart;
        }

        int unusedNum = (int) (seatTotalNum * direstPart) - solidTicketSize;
        numOfLeftTicket += unusedNum;

        return new Response<>(1, "Get Left Ticket of Internal Success", numOfLeftTicket);
    }

    private double getDirectProportion(HttpHeaders headers) {

        String configName = "DirectTicketAllocationProportion";
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<Response<Config>> re = restTemplate.exchange(
                "http://ts-config-service:15679/api/v1/configservice/configs/" + configName,
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<Response<Config>>() {
                });
        Response<Config> configValue = re.getBody();
        GetLeftTicketOfIntervalServiceImpl.LOGGER.info("Configs is : {}", configValue.getData().toString());
        return Double.parseDouble(configValue.getData().getValue());
    }
}
