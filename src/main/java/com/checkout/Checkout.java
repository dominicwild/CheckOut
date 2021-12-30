package com.checkout;

import java.math.BigDecimal;
import java.util.List;

import com.promotions.Promotion;
import com.promotions.PromotionType;

public class Checkout {

	private List<Promotion> promotions;

	public static BigDecimal totalPriceOf(List<CheckoutItem> items) {
		return items.stream()
				.map(CheckoutItem::getPrice)
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	public void applyPromotion(PromotionType promotionType) {
		promotions.add(promotionType.getPromotion());
	}

	public BigDecimal totalPriceWithPromotionsOf(List<CheckoutItem> items) {
		BigDecimal totalPrice = totalPriceOf(items);
		return promotions.stream()
				.map(promotion -> promotion.discountFor(items))
				.reduce(totalPrice, BigDecimal::add);
	}

}
