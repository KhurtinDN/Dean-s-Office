package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils;

import java.util.Arrays;

/**
 * @author Denis Khurtin
 */
public class ObjectUtil {
    public static boolean equal(final Object a, final Object b) {
        return a == b || (a != null && a.equals(b));
    }

    public static int hashCode(final Object... objects) {
        return Arrays.hashCode(objects);
    }
}
