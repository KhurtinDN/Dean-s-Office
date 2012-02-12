package ru.sgu.csit.inoc.deansoffice;

import java.util.UUID;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CommonTestUtils {

    public static Long generateId() {
        return UUID.randomUUID().getLeastSignificantBits();
    }

    public static void testWhenObjectsAreDifferent(final Object testee1, final Object testee2) {

        // ACT
        boolean equals12 = testee1.equals(testee2);
        boolean equals21 = testee2.equals(testee1);

        // CHECK
        assertFalse("1 not equals 2", equals12);
        assertFalse("2 not equals 1", equals21);
    }

    public static void testWhenObjectsAreEqual(final Object testee1, final Object testee2) {

        // ACT
        boolean equals12 = testee1.equals(testee2);
        boolean equals21 = testee2.equals(testee1);
        int hashCode1 = testee1.hashCode();
        int hashCode2 = testee2.hashCode();

        // CHECK
        assertTrue("1 equals 2", equals12);
        assertTrue("2 equals 1", equals21);
        assertTrue("hashCodes are equal", hashCode1 == hashCode2);
    }
}
