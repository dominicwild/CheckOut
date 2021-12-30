package com.mock;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.CheckoutItem;
import com.promotions.Promotion;
import com.promotions.interfaces.PromotionType;

public class TestPromotion implements Promotion, PromotionType {

	private BigDecimal discount;

	public TestPromotion(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	public BigDecimal discountFor(List<CheckoutItem> items) {
		return discount;
	}

	@Override
	public Promotion getPromotion() {
		return this;
	}

}