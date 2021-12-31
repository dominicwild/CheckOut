package com.promotions.types;

import static java.util.stream.Collectors.toList;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Predicate;

import com.checkout.CheckoutItem;
import com.promotions.types.interfaces.Promotion;

public class WaterBottlePromotion implements Promotion {

	private static final String QUALIFYING_PRODUCT_NAME = "Water Bottle";
	public static final BigDecimal PRICE_REDUCTION = BigDecimal.valueOf(1.96);
	public static final BigDecimal REDUCED_PRICE = BigDecimal.valueOf(22.99);

	@Override
	public BigDecimal discountFor(List<CheckoutItem> items) {
		if (numberOfWaterBottlesFrom(items) >= 2) {
			waterBottlesFrom(items).forEach(waterBottle -> waterBottle.setPrice(REDUCED_PRICE));
		}

		return BigDecimal.ZERO;
	}

	private Iterable<CheckoutItem> waterBottlesFrom(List<CheckoutItem> items) {
		return items.stream()
				.filter(isAWaterBottle())
				.collect(toList());
	}

	private int numberOfWaterBottlesFrom(List<CheckoutItem> items) {
		return items.stream()
				.filter(isAWaterBottle())
				.map(CheckoutItem::getQuantity)
				.reduce(0, Integer::sum);
	}

	private Predicate<? super CheckoutItem> isAWaterBottle() {
		return item -> item.getName().equals(QUALIFYING_PRODUCT_NAME);
	}

	public void adjustPriceOf(List<CheckoutItem> items) {
		if (numberOfWaterBottlesFrom(items) >= 2) {
			waterBottlesFrom(items).forEach(waterBottle -> waterBottle.setPrice(REDUCED_PRICE));
		}
	}

}
