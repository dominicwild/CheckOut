package com.promotions.types.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.CheckoutItem;

public interface Promotion {

    default BigDecimal discountFor(List<CheckoutItem> items) {
        return BigDecimal.ZERO;
    }

    default void adjustPriceOf(List<CheckoutItem> items) {
    }

}
