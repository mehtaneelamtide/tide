package com.tide.test.core.entity;

import com.tide.test.core.usercase.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Amount {

    // assumed same currency across the application
    private static final Currency DEFAULT_CURRENCY = Currency.getInstance("GBP");
    private BigDecimal amount;
    private static final RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;


    public Amount(BigDecimal amount) {

        if(amount !=null && amount.compareTo(BigDecimal.ZERO) >= 0){ // bid amount cannot be negative or zero
            this.amount = amount.setScale(DEFAULT_CURRENCY.getDefaultFractionDigits(), DEFAULT_ROUNDING);;
        }else {
            throw new InvalidAmountException();
        }
    }

    public BigDecimal getAmount() {
        return amount;
    }


}
