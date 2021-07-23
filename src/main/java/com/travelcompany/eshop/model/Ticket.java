package com.travelcompany.eshop.model;

import lombok.Data;

@Data
public class Ticket {

    private int id;
    private int itineraryId;
    private double amountPaid;
    private int passengerId;
    private PayType paymentMethod;
}
