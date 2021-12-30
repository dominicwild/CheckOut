package com.checkout;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.promotions.types.interfaces.Promotion;
import com.promotions.types.interfaces.PromotionType;

public class Checkout {

	private List<Promotion> promotions = new ArrayList<>();

	public static BigDecimal totalPriceOf(List<CheckoutItem> items) {
		return items.stream()
				.map(item -> priceOf(item).multiply(quantityOf(item)))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
	}

	private static BigDecimal priceOf(CheckoutItem item) {
		return item.getPrice();
	}

	private static BigDecimal quantityOf(CheckoutItem item) {
		return new BigDecimal(item.getQuantity());
	}

	public void applyPromotion(PromotionType promotionType) {
		promotions.add(promotionType.getPromotion());
	}

	public BigDecimal totalPriceWithPromotionsOf(List<CheckoutItem> items) {
		BigDecimal totalPrice = totalPriceOf(items);
		return promotions.stream()
				.map(promotion -> promotion.discountFor(items).negate())
				.reduce(totalPrice, BigDecimal::add);
	}

}
