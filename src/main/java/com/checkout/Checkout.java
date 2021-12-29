package com.checkout;

import java.math.BigDecimal;
import java.util.List;

public class Checkout {

    public BigDecimal totalOf(List<CheckoutItem> items) {
        return items.stream()
                .map(CheckoutItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
