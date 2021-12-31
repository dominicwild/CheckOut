package com.acceptance;

import static com.checkout.CheckoutItemType.HOODIE;
import static com.checkout.CheckoutItemType.STICKER_SET;
import static com.checkout.CheckoutItemType.WATER_BOTTLE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.Checkout;
import com.checkout.CheckoutItem;
import com.promotions.CheckoutPromotion;
import com.promotions.types.Over75PoundPromotion;
import com.promotions.types.WaterBottlePromotion;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class CheckoutAcceptanceTests {

	@Test
	@Disabled("This test is not yet implemented")
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

}
