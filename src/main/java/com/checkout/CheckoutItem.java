package com.checkout;

import java.math.BigDecimal;

public class CheckoutItem {

    private int id;
    private String name;
    private BigDecimal amount;

    public CheckoutItem(int id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.amount = BigDecimal.valueOf(amount);
    }

    public int getPrice() {
        return 0;
    }

}
