package com.promotions.types.interfaces;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.CheckoutItem;

public interface Promotion {

    BigDecimal discountFor(List<CheckoutItem> items);

}
