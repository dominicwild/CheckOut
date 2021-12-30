package com.checkout;

import java.math.BigDecimal;

public class CheckoutItem {

    private int id;
    private String name;
    private BigDecimal price;

    public CheckoutItem(int id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.price = BigDecimal.valueOf(amount);
    }

    public CheckoutItem(Integer id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

}
