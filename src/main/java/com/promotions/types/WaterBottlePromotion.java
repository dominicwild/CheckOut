package com.promotions.types;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.CheckoutItem;
import com.promotions.types.interfaces.Promotion;

public class WaterBottlePromotion implements Promotion {

    private static final String QUALIFYING_PRODUCT_NAME = "Water Bottle";
    public static final BigDecimal PRICE_REDUCTION = BigDecimal.valueOf(1.96);

    @Override
    public BigDecimal discountFor(List<CheckoutItem> items) {
        long numberOfWaterBottles = numberOfWaterBottlesFrom(items);

        if (numberOfWaterBottles >= 2) {
            return new BigDecimal(numberOfWaterBottles).multiply(PRICE_REDUCTION);
        }

        return BigDecimal.ZERO;
    }

    private int numberOfWaterBottlesFrom(List<CheckoutItem> items) {
        return items.stream()
                .filter(item -> item.getName().equals(QUALIFYING_PRODUCT_NAME))
                .map(CheckoutItem::getQuantity)
                .reduce(0, Integer::sum);
    }

}
