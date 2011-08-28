package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils;

/**
 * @author Denis Khurtin
 */
public class Validate {
    public static void notNull(final Object object, final String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
