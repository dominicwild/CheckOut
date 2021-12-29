package com.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

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

}
