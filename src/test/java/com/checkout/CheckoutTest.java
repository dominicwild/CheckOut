package com.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		int total = checkout.totalOf(items);

		assertEquals(0, total);
	}

	@ParameterizedTest
	@EnumSource
	void checkout_with_one_item_returns_cost_of_that_item(CheckoutItemType itemType) {
		Checkout checkout = new Checkout();

		List<CheckoutItem> items = new ArrayList<>();
		CheckoutItem item = itemType.newItem();
		items.add(item);
		int total = checkout.totalOf(items);

		int expectedTotal = item.getPrice();
		assertEquals(expectedTotal, total);
	}

}
