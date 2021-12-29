package com.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class CheckoutTest {
    
    @Test
    void checkout_with_zero_items_has_zero_total(){
        Checkout checkout = new Checkout();
        
        List<CheckoutItem> items = new ArrayList<>();
        int total = checkout.totalOf(items);

        assertEquals(0, total);
    }

}
