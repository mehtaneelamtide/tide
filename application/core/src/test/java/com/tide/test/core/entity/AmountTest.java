package com.tide.test.core.entity;

import com.tide.test.core.usercase.exceptions.InvalidAmountException;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class AmountTest {

    @Test(expected = InvalidAmountException.class)
    public void amountCannotBeNegative(){
        Amount amount = new Amount(BigDecimal.valueOf(-1));
    }



    @Test
    public void amountCanBeAnyPositiveAmount(){
        Amount amount = new Amount(BigDecimal.TEN);
        BigDecimal expected = BigDecimal.TEN.setScale(Currency.getInstance("GBP").getDefaultFractionDigits(),RoundingMode.HALF_EVEN);
        Assert.assertEquals(expected, amount.getAmount() );

    }
}
