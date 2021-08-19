package com.travelcompany.eshop.services;

import com.travelcompany.eshop.model.Category;
import com.travelcompany.eshop.model.Customer;
import com.travelcompany.eshop.model.PayType;

public class CustomerService extends Customer {

    private static final double BUSINESS_DISCOUNT = -0.1;
    private static final double INDIVIDUAL_SURCHARGE = 0.2;
    private static final double CREDIT_PAYMENT = -0.1;

    public double discounts(double amount, PayType payType, Category category) {

        if (payType.equals(CREDIT_PAYMENT)){
            amount = amount * CREDIT_PAYMENT;
            paymentDiscount(amount, category);
        }
        return amount;
    }


    public static double paymentDiscount(double amount, Category category){
        if (category.equals(Category.Individual))
            amount = (amount * INDIVIDUAL_SURCHARGE);
        else
            amount = (amount * BUSINESS_DISCOUNT);
        return amount;
    }
}