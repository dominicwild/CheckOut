package com.checkout.promotions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.CheckoutItem;
import com.checkout.fixture.CheckoutItemFixture;
import com.promotions.Over75PoundPromotion;

import org.junit.jupiter.api.Test;

class Over75PoundPromotionTest {

    @Test
    void items_under_75_pound_have_no_discount() {
        double under75PoundPrice = 44.50;
        List<CheckoutItem> items = List.of(CheckoutItemFixture.ofPrice(under75PoundPrice));

        Over75PoundPromotion promotion = new Over75PoundPromotion();
        BigDecimal discount = promotion.discountFor(items);

        assertEquals(BigDecimal.ZERO, discount);
    }

}
