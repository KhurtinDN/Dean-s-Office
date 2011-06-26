package ru.sgu.csit.inoc.deansoffice;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * User: hd
 * Date: 5/29/11
 * Time: 8:32 PM
 */
public final class CommonTestUtils {
    public static void testWhenObjectsAreDifferent(final Object testee1, final Object testee2) {

        // ACT
        final boolean equals12 = testee1.equals(testee2);
        final boolean equals21 = testee2.equals(testee1);

        // CHECK
        assertThat("1 not equals 2", equals12, is(false));
        assertThat("2 not equals 1", equals21, is(false));
    }

    public static void testWhenObjectsAreEqual(final Object testee1, final Object testee2) {

        // ACT
        final boolean equals12 = testee1.equals(testee2);
        final boolean equals21 = testee2.equals(testee1);
        final int hashCode1 = testee1.hashCode();
        final int hashCode2 = testee2.hashCode();

        // CHECK
        assertThat("1 equals 2", equals12, is(true));
        assertThat("2 equals 1", equals21, is(true));
        assertThat("hashCodes are equal", hashCode1 == hashCode2, is(true));
    }
}
