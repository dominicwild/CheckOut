package com.checkout;

public enum CheckoutItemType {
    WATER_BOTTLE(1, "Water Bottle", 24.95), HOODIE(2, "Hoodie", 65), STICKER_SET(3, "Sticker Set", 3.99);

    private int id;
    private String name;
    private double amount;

    CheckoutItemType(int id, String name, double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public CheckoutItem newItem() {
        return new CheckoutItem(id, name, amount);
    }

    public CheckoutItem ofQuantity(int quantity) {
        return new CheckoutItem(id, name, amount).quantity(quantity);
    }

}
