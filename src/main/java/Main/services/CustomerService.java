package Main.services;

import Main.model.Category;
import Main.model.Customer;
import Main.model.PayType;

public class CustomerService extends Customer {


    private static final double BUSINESS_DISCOUNT = -0.1;
    private static final double INDIVIDUAL_SURCHARGE = 0.2;
    private static final double CREDIT_PAYMENT = -0.1;

    private static double discounts(double amount, PayType payType, Category category) {

        if (category.equals(Category.INDIVIDUAL)) {
            amount = (amount * INDIVIDUAL_SURCHARGE);
            if (payType.equals(PayType.CREDIT_CARD))
                amount = (amount * CREDIT_PAYMENT);
        } else {
            amount = (amount * BUSINESS_DISCOUNT);
            if (payType.equals(PayType.CREDIT_CARD))
                amount = (amount * CREDIT_PAYMENT);
        }

        return amount;
    }
}