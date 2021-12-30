package com.promotions;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.checkout.Checkout;
import com.checkout.CheckoutItem;

public class Over75PoundPromotion implements Promotion {

	private static final BigDecimal QUALIFYING_PRICE = BigDecimal.valueOf(75.0);
	private static final BigDecimal TEN_PERCENT_MULTIPLIER = BigDecimal.valueOf(0.1);

	@Override
	public BigDecimal discountFor(List<CheckoutItem> items) {
		BigDecimal totalPrice = Checkout.totalPriceOf(items);

		if (isLessThanQualifyingPrice(totalPrice)) {
			return BigDecimal.ZERO;
		}

		return tenPercentOf(totalPrice).setScale(2, RoundingMode.HALF_UP);
	}

	private boolean isLessThanQualifyingPrice(BigDecimal totalPrice) {
		return totalPrice.compareTo(QUALIFYING_PRICE) < 1;
	}

	private BigDecimal tenPercentOf(BigDecimal totalPrice) {
		return totalPrice.multiply(TEN_PERCENT_MULTIPLIER);
	}

}
