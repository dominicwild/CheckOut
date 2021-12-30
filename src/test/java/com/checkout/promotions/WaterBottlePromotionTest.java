package com.checkout.promotions;

import static com.checkout.CheckoutItemType.WATER_BOTTLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.Checkout;
import com.checkout.CheckoutItem;
import com.promotions.types.WaterBottlePromotion;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class WaterBottlePromotionTest {

	@ParameterizedTest
	@ValueSource(ints = { 0, 1 })
	void less_than_two_water_bottles_are_not_eligible_for_promotion(int quantity) {
		CheckoutItem waterBottles = WATER_BOTTLE.ofQuantity(quantity);
		List<CheckoutItem> items = List.of(waterBottles);

		WaterBottlePromotion promotion = new WaterBottlePromotion();

		BigDecimal discount = promotion.discountFor(items);
		BigDecimal zeroDiscount = BigDecimal.ZERO;
		assertEquals(zeroDiscount, discount);
	}

}
