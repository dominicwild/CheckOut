package com.promotions;

import com.promotions.types.Over75PoundPromotion;
import com.promotions.types.interfaces.PromotionType;

public enum CheckoutPromotion implements PromotionType {
    OVER_75_POUND(new Over75PoundPromotion());

    private Promotion promotion;

    CheckoutPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Promotion getPromotion() {
        return promotion;
    }

}
