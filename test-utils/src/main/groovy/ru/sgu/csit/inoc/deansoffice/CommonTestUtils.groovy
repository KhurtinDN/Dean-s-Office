package ru.sgu.csit.inoc.deansoffice

import static org.junit.Assert.assertFalse
import static org.junit.Assert.assertTrue

public final class CommonTestUtils {

    public static Long generateId() {
        UUID.randomUUID().leastSignificantBits
    }

    public static void testWhenObjectsAreDifferent(final Object testee1, final Object testee2) {

        // ACT
        final equals12 = testee1.equals(testee2);
        final equals21 = testee2.equals(testee1);

        // CHECK
        assertFalse "1 not equals 2", equals12
        assertFalse "2 not equals 1", equals21
    }

    public static void testWhenObjectsAreEqual(final Object testee1, final Object testee2) {

        // ACT
        final equals12 = testee1.equals(testee2);
        final equals21 = testee2.equals(testee1);
        final hashCode1 = testee1.hashCode();
        final hashCode2 = testee2.hashCode();

        // CHECK
        assertTrue "1 equals 2", equals12
        assertTrue "2 equals 1", equals21
        assertTrue "hashCodes are equal", hashCode1 == hashCode2
    }
}

