package com.andile.flight.service.utils;

import com.andile.flight.service.exception.InsufficientFundsException;

import java.util.HashMap;
import java.util.Map;

public class PaymentUtils {

    private static Map<String, Double> paymentMap = new HashMap<>();
    static {
        paymentMap.put("account1",65000.0);
        paymentMap.put("account2",5000.0);
        paymentMap.put("account3",200.0);
        paymentMap.put("account4",600.0);
    }
    /**
     * This method is used to validate the Credit Limit on the account
     * @param accNo represents the account number to be validated
     * @param paidAmount represents the amount tp be paid
     * @return boolean true if the amount is valid
     * */
    public static boolean validateCreditLimit(String accNo, double paidAmount){
        if (paidAmount > paymentMap.get(accNo)){
            throw new InsufficientFundsException("Insufficient funds..!");
        }else {
            return true;
        }
    }
}
