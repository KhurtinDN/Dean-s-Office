package ru.sgu.csit.inoc.deansoffice.reports.reportsutil;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by XX (MesheryakovAV)
 * Date: 03.09.2010
 * Time: 12:07:40
 */
public class FontDescriptor {
    public static final String[] FONT_LSERIF;
    public static final String[] FONT_LSANS;
    public static final String[] FONT_LMONO;

    static {
        final Properties properties = new Properties();

        final InputStream propertiesInputStream = FontDescriptor.class.getResourceAsStream("/fonts.properties");
        if (propertiesInputStream != null) {
            try {
                properties.load(propertiesInputStream);
            } catch (IOException e) {
                throw new RuntimeException("Failed to load properties from fonts.properties.", e);
            }
        } else {
            throw new RuntimeException("fonts.properties not found in the classpath.");
        }

        String dir = properties.getProperty("FontDirectory");
        if (!dir.isEmpty() && !dir.endsWith(File.separator)) {
            dir += File.separator;
        }

        FONT_LSERIF = new String[] {
                dir + properties.getProperty("SerifRegular"),
                dir + properties.getProperty("SerifBold"),
                dir + properties.getProperty("SerifItalic"),
                dir + properties.getProperty("SerifBoldItalic")
        };

        FONT_LSANS = new String[] {
                dir + properties.getProperty("SansRegular"),
                dir + properties.getProperty("SansBold"),
                dir + properties.getProperty("SansItalic"),
                dir + properties.getProperty("SansBoldItalic")
        };

        FONT_LMONO = new String[] {
                dir + properties.getProperty("MonoRegular"),
                dir + properties.getProperty("MonoBold"),
                dir + properties.getProperty("MonoItalic"),
                dir + properties.getProperty("MonoBoldItalic")
        };
    }
}
