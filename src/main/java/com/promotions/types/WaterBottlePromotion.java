package com.promotions.types;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.CheckoutItem;
import com.promotions.Promotion;

public class WaterBottlePromotion implements Promotion {

    @Override
    public BigDecimal discountFor(List<CheckoutItem> items) {
        return BigDecimal.ZERO;
    }
    
}
