package com.example.getOrderById.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.UUID;

/**
 * @author fdse
 */
@Data
@Document(collection = "orders")
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order {

    @Id
    private UUID id;

    private Date boughtDate;


    private Date travelDate;


    private Date travelTime;

    /**
     * Which Account Bought it
     */
    private UUID accountId;

    /**
     * Tickets bought for whom....
     */
    private String contactsName;

    private int documentType;

    private String contactsDocumentNumber;

    private String trainNumber;

    private int coachNumber;

    private int seatClass;

    private String seatNumber;

    private String from;

    private String to;

    private int status;

    private String price;

}
