package com.checkout;

import java.math.BigDecimal;

public class CheckoutItem {

    private int id;
    private String name;
    private BigDecimal price;
    private int quantity = 1;

    public CheckoutItem(int id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.price = BigDecimal.valueOf(amount);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Object getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public CheckoutItem quantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

}
