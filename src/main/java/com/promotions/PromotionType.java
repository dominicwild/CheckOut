package com.promotions;

public enum PromotionType {
    OVER_75_POUND(new Over75PoundPromotion());

    private Promotion promotion;

    PromotionType(Promotion promotion) {
        this.promotion = promotion;
    }

    public Promotion getPromotion() {
        return null;
    }

}
