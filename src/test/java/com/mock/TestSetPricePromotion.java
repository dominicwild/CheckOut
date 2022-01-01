package com.mock;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.CheckoutItem;
import com.promotions.types.interfaces.Promotion;
import com.promotions.types.interfaces.PromotionType;

public class TestSetPricePromotion implements Promotion, PromotionType{

    private BigDecimal setPrice;

    public TestSetPricePromotion(BigDecimal setPrice) {
        this.setPrice = setPrice;
    }

    @Override
    public void adjustPriceOf(List<CheckoutItem> items) {
        items.forEach(item -> item.setPrice(setPrice));
    }

    @Override
    public Promotion getPromotion() {
        return this;
    }

}
