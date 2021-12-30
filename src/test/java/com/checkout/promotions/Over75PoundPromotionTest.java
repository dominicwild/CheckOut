package com.checkout.promotions;

import static com.checkout.assertions.CustomAssertions.assertBigDecimalEqual;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import com.checkout.CheckoutItem;
import com.fixture.CheckoutItemFixture;
import com.promotions.Over75PoundPromotion;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class Over75PoundPromotionTest {

    @ParameterizedTest
    @ValueSource(doubles = { 0, 45.50, 75 })
    void items_under_75_pound_have_no_discount(double under75PoundPrice) {
        List<CheckoutItem> items = List.of(CheckoutItemFixture.ofPrice(under75PoundPrice));

        Over75PoundPromotion promotion = new Over75PoundPromotion();
        BigDecimal discount = promotion.discountFor(items);

        assertBigDecimalEqual(BigDecimal.ZERO, discount);
    }

    @Test
    void items_over_75_pound_have_discount_of_10_percent_total_price() {
        double over75PoundPrice = 75.01;
        List<CheckoutItem> items = List.of(CheckoutItemFixture.ofPrice(over75PoundPrice));

        Over75PoundPromotion promotion = new Over75PoundPromotion();
        BigDecimal discount = promotion.discountFor(items);

        BigDecimal tenPercentOfPrice = BigDecimal.valueOf(7.50);
        assertBigDecimalEqual(tenPercentOfPrice, discount);
    }

}
