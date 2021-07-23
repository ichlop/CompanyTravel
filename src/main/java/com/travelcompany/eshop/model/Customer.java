package com.travelcompany.eshop.model;

import lombok.Data;

@Data
public class Customer {
    private int id;
    private String name;
    private String email;
    private String addressCity;
    private String nationality;
    private Category category;
    private PayType payType;

}
