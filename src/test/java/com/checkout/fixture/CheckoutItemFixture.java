package com.checkout.fixture;

import static java.lang.Math.abs;
import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import com.checkout.CheckoutItem;

public class CheckoutItemFixture {

    private static final Random rand = new Random();

    public static List<CheckoutItem> generateRandomItems(int numberOfItems) {
        return Stream.generate(CheckoutItemFixture::randomItem)
                .limit(numberOfItems)
                .collect(toList());
    }

    private static CheckoutItem randomItem() {
        double randomPriceDouble = abs(rand.nextDouble() * 10_000);
        int randomId = rand.nextInt();
        BigDecimal randomPrice = BigDecimal.valueOf(randomPriceDouble).setScale(2, RoundingMode.HALF_UP);

        return new CheckoutItem(randomId, "Item" + randomId, randomPrice.doubleValue());
    }

    public static CheckoutItem ofPrice(double priceOfItem) {
        return new CheckoutItem(1, "Item 1", priceOfItem);
    }

}
