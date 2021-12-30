package com.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fixture.CheckoutItemFixture;
import com.mock.TestPromotion;
import com.promotions.types.interfaces.PromotionType;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

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

	@ParameterizedTest
	@ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
	void checkout_applies_discounts_from_promotions_off_the_total(int numberOfPromotions) {
		double priceOfItem1 = 10.00;

		List<CheckoutItem> items = List.of(CheckoutItemFixture.ofPrice(priceOfItem1));

		double promotionDiscount = 1.00;
		PromotionType testPromotion = new TestPromotion(BigDecimal.valueOf(promotionDiscount));
		
		Checkout checkout = new Checkout();
		for (int i = 0; i < numberOfPromotions; i++) {
			checkout.applyPromotion(testPromotion);
		}

		BigDecimal checkoutTotal = checkout.totalPriceWithPromotionsOf(items);

		double expectedDiscountPriceTotal = priceOfItem1 - (numberOfPromotions * promotionDiscount);
		BigDecimal expectedDiscountedTotal = new BigDecimal(expectedDiscountPriceTotal + "");
		assertEquals(expectedDiscountedTotal, checkoutTotal);
	}
}
