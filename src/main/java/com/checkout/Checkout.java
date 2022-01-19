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

	public void addPromotion(PromotionType promotionType) {
		promotions.add(promotionType.getPromotion());
	}

	public BigDecimal totalPriceWithPromotionsOf(List<CheckoutItem> items) {
		adjustPricesByPromotionsFor(items);
		BigDecimal totalDiscount = totalDiscountFor(items);
		BigDecimal totalPrice = totalPriceOf(items);

		return totalPrice.add(totalDiscount);
	}

	private void adjustPricesByPromotionsFor(List<CheckoutItem> items) {
		promotions.forEach(promotion -> promotion.adjustPriceOf(items));
    }

    private BigDecimal totalDiscountFor(List<CheckoutItem> items) {
		return promotions.stream()
				.map(promotion -> promotion.discountFor(items))
				.reduce(BigDecimal.ZERO, BigDecimal::add).negate();
	}

}
