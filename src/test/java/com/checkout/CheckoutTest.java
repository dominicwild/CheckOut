package com.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.checkout.fixture.CheckoutItemFixture;
import com.promotions.PromotionType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class CheckoutTest {

	@Test
	void checkout_with_zero_items_has_zero_total() {
		List<CheckoutItem> items = new ArrayList<>();
		BigDecimal total = Checkout.totalPriceOf(items);

		assertEquals(BigDecimal.ZERO, total);
	}

	@ParameterizedTest
	@EnumSource
	void checkout_with_one_item_returns_cost_of_that_item(CheckoutItemType itemType) {

		List<CheckoutItem> items = List.of(itemType.newItem());
		BigDecimal total = Checkout.totalPriceOf(items);

		BigDecimal expectedTotal = items.get(0).getPrice();
		assertEquals(expectedTotal, total);
	}

	@Test
	void checkout_with_two_items_returns_total_cost_of_both_items() {
		double priceOfItem1 = 30.5;
		double priceOfItem2 = 70.5;

		List<CheckoutItem> items = List.of(
				CheckoutItemFixture.ofPrice(priceOfItem1),
				CheckoutItemFixture.ofPrice(priceOfItem2));

		BigDecimal checkoutTotal = Checkout.totalPriceOf(items);

		BigDecimal expectedTotal = BigDecimal.valueOf(101.0);
		assertEquals(expectedTotal, checkoutTotal);
	}

	@Test
	void checkout_with_multiple_items_returns_sum() {
		int numberOfItems = 100;
		List<CheckoutItem> items = CheckoutItemFixture.generateRandomItems(numberOfItems);

		BigDecimal checkoutTotal = Checkout.totalPriceOf(items);

		BigDecimal expectedTotal = items.stream()
				.map(CheckoutItem::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		assertEquals(expectedTotal, checkoutTotal);
	}

	@Test
	void checkout_with_over_75_pound_promotion_applied_returns_10_percent_off() {
		double priceOfItem1 = 75.00;
		double priceOfItem2 = 0.01;

		List<CheckoutItem> items = List.of(
				CheckoutItemFixture.ofPrice(priceOfItem1),
				CheckoutItemFixture.ofPrice(priceOfItem2));

		Checkout checkout = new Checkout();
		checkout.applyPromotion(PromotionType.OVER_75_POUND);
		BigDecimal checkoutTotal = checkout.totalPriceWithPromotionsOf(items);

		double tenPercentOffExpectedTotal = 67.51;
		BigDecimal expectedDiscountedTotal = new BigDecimal(tenPercentOffExpectedTotal + "");
		assertEquals(expectedDiscountedTotal, checkoutTotal);
	}
}
