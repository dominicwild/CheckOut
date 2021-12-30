package com.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.quicktheories.generators.SourceDSL.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.quicktheories.QuickTheory;
import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;
import org.quicktheories.generators.SourceDSL;

public class CheckoutTest {

	@Test
	void checkout_with_zero_items_has_zero_total() {
		Checkout checkout = new Checkout();

		List<CheckoutItem> items = new ArrayList<>();
		BigDecimal total = checkout.totalOf(items);

		assertEquals(BigDecimal.ZERO, total);
	}

	@ParameterizedTest
	@EnumSource
	void checkout_with_one_item_returns_cost_of_that_item(CheckoutItemType itemType) {
		Checkout checkout = new Checkout();

		List<CheckoutItem> items = List.of(itemType.newItem());
		BigDecimal total = checkout.totalOf(items);

		BigDecimal expectedTotal = items.get(0).getPrice();
		assertEquals(expectedTotal, total);
	}

	@Test
	void checkout_with_two_items_returns_total_cost_of_both_items() {
		Checkout checkout = new Checkout();
		double priceOfItem1 = 30.5;
		double priceOfItem2 = 70.5;

		List<CheckoutItem> items = List.of(
				new CheckoutItem(1, "Item 1", priceOfItem1),
				new CheckoutItem(2, "Item 2", priceOfItem2));

		BigDecimal checkoutTotal = checkout.totalOf(items);

		BigDecimal expectedTotal = BigDecimal.valueOf(priceOfItem1 + priceOfItem2);
		assertEquals(expectedTotal, checkoutTotal);
	}

	@Test
	void checkout_with_multiple_items_returns_sum() {
		QuickTheory.qt().forAll(randomItems())
				.check(itemsArray -> {
					Checkout checkout = new Checkout();
					List<CheckoutItem> items = Arrays.asList(itemsArray);
					BigDecimal total = checkout.totalOf(Arrays.asList(itemsArray));
					BigDecimal expectedTotal = items.stream()
							.map(CheckoutItem::getPrice)
							.reduce(BigDecimal.ZERO, BigDecimal::add);
					return total.equals(expectedTotal);
				});
	}

	public static Gen<CheckoutItem[]> randomItems() {
		return arrays().ofClass(randomItem(), CheckoutItem.class).withLengthBetween(1, 1000);
	}

	public static Gen<CheckoutItem> randomItem() {
		return randomIds().zip(randomNames(), randomPrices(), (id, name, price) -> new CheckoutItem(id, name, price));
	}

	private static Gen<BigDecimal> randomPrices() {
		return bigDecimals().ofBytes(8).withScale(2).map(price -> {
			if (price.compareTo(BigDecimal.ZERO) > 0) {
				return price;
			} else {
				return price.negate();
			}
		});
	}

	private static Gen<String> randomNames() {
		return strings().basicLatinAlphabet().ofLengthBetween(1, 100);
	}

	private static Gen<Integer> randomIds() {
		return integers().allPositive();
	}

}
