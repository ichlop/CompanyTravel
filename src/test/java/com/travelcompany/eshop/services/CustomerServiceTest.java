package com.travelcompany.eshop.services;

import com.travelcompany.eshop.model.Category;
import com.travelcompany.eshop.model.PayType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void discounts() {
    }

    @Test
    void paymentDiscount() {
    }

    @Test
    public void testDiscounts() {
        System.out.println("discounts");
        double amount = 100000.0;
        PayType payType = PayType.Creditcard;
        Category category = Category.Business;
        CustomerService instance = new CustomerService();
        double expResult = 980100;
        double result = instance.discounts(amount, payType, category);
        assertEquals(expResult, result, 0.0);
    }

}