package com.acceptance;

import static com.fixture.CheckoutItemType.HOODIE;
import static com.fixture.CheckoutItemType.STICKER_SET;
import static com.fixture.CheckoutItemType.WATER_BOTTLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.Checkout;
import com.checkout.CheckoutItem;
import com.promotions.CheckoutPromotion;

import org.junit.jupiter.api.Test;

class CheckoutAcceptanceTests {

	@Test
	void checkout_with_water_bottle_promotion_and_over_75_pound_promotion() {
		Checkout checkout = new Checkout();
		checkout.applyPromotion(CheckoutPromotion.OVER_75_POUND);
		checkout.applyPromotion(CheckoutPromotion.WATER_BOTTLE_PROMOTION);

		List<CheckoutItem> items = List.of(
				WATER_BOTTLE.ofQuantity(2),
				HOODIE.ofQuantity(1),
				STICKER_SET.ofQuantity(1));

		BigDecimal totalPrice = checkout.totalPriceWithPromotionsOf(items);

		BigDecimal expectedPrice = BigDecimal.valueOf(103.47);
		assertEquals(expectedPrice, totalPrice);
	}

	@Test
	void checkout_with_water_bottle_promotion_and_three_water_bottles() {
		Checkout checkout = new Checkout();
		checkout.applyPromotion(CheckoutPromotion.OVER_75_POUND);
		checkout.applyPromotion(CheckoutPromotion.WATER_BOTTLE_PROMOTION);

		List<CheckoutItem> items = List.of(WATER_BOTTLE.ofQuantity(3));

		BigDecimal totalPrice = checkout.totalPriceWithPromotionsOf(items);

		BigDecimal expectedPrice = BigDecimal.valueOf(68.97);
		assertEquals(expectedPrice, totalPrice);
	}

	@Test
	void checkout_with_over_75_pound_promotion_and_two_hoodies_and_a_sticker_set() {
		Checkout checkout = new Checkout();
		checkout.applyPromotion(CheckoutPromotion.OVER_75_POUND);
		checkout.applyPromotion(CheckoutPromotion.WATER_BOTTLE_PROMOTION);

		List<CheckoutItem> items = List.of(
				HOODIE.ofQuantity(2),
				STICKER_SET.ofQuantity(1));

		BigDecimal totalPrice = checkout.totalPriceWithPromotionsOf(items);

		BigDecimal expectedPrice = BigDecimal.valueOf(120.59);
		assertEquals(expectedPrice, totalPrice);
	}

}
