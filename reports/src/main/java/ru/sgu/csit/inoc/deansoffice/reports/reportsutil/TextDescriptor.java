package ru.sgu.csit.inoc.deansoffice.reports.reportsutil;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by XX (MesheryakovAV)
 * Date: 03.09.2010
 * Time: 12:07:40
 */
public class TextDescriptor {
    public final static int firstLineIndent = 40;

    public static String[] FONT_LSERIF = null;

    static {
        Properties properties = new Properties();

        InputStream propertiesInputStream = TextDescriptor.class.getResourceAsStream("/fonts.properties");
        if (propertiesInputStream != null) {
            try {
                properties.load(propertiesInputStream);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        FONT_LSERIF = new String[] {
                properties.getProperty("Regular", "/home/hd/liberation-fonts/LiberationSerif-Regular.ttf"),
                properties.getProperty("Bold", "/home/hd/liberation-fonts/LiberationSerif-Bold.ttf"),
                properties.getProperty("Italic", "/home/hd/liberation-fonts/LiberationSerif-Italic.ttf"),
                properties.getProperty("BoldItalic", "/home/hd/liberation-fonts/LiberationSerif-BoldItalic.ttf")
        };
    }
}
