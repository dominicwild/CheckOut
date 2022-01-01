package com.checkout.promotions;

import static com.fixture.CheckoutItemType.WATER_BOTTLE;
import static com.promotions.types.WaterBottlePromotion.REDUCED_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

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

		BigDecimal priceBeforePromotion = waterBottles.getPrice();
		promotion.adjustPriceOf(items);

		BigDecimal priceAfterPromotion = waterBottles.getPrice();
		assertEquals(priceAfterPromotion, priceBeforePromotion);
	}

	@ParameterizedTest
	@ValueSource(ints = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 100 })
	void two_or_more_water_bottles_are_set_to_reduced_price(int quantity) {
		CheckoutItem waterBottles = WATER_BOTTLE.ofQuantity(quantity);
		List<CheckoutItem> items = List.of(waterBottles);

		WaterBottlePromotion promotion = new WaterBottlePromotion();
		promotion.adjustPriceOf(items);

		assertEquals(REDUCED_PRICE, waterBottles.getPrice());
	}

}
