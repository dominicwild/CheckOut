package com.promotions;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.Checkout;
import com.checkout.CheckoutItem;

public class Over75PoundPromotion implements Promotion {

	@Override
	public BigDecimal discountFor(List<CheckoutItem> items) {
		return BigDecimal.ZERO;
	}

}
