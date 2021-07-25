package com.travelcompany.eshop.model;

import lombok.Data;

import java.util.Date;

@Data
public class Itinerary{
    private int id;
    private String departureAirportId;
    private String destinationAirportId;
    private Date departureDate;
    private String airline;
    private int price;

}
