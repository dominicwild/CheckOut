package com.assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

public class CustomAssertions {
    public static void assertBigDecimalEqual(BigDecimal b1, BigDecimal b2) {
        int compareResult = b1.compareTo(b2);
        assertEquals(0, compareResult, b1.toPlainString() + " is not equal to " + b2.toPlainString());
    }

}
