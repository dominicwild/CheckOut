package com.promotions;

import com.promotions.interfaces.PromotionType;

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
