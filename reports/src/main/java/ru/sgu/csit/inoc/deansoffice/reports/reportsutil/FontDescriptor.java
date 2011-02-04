package ru.sgu.csit.inoc.deansoffice.reports.reportsutil;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by XX (MesheryakovAV)
 * Date: 03.09.2010
 * Time: 12:07:40
 */
public class FontDescriptor {
    public final static int firstLineIndent = 40;

    public static String[] FONT_LSERIF = null;
    public static String[] FONT_LSANS = null;
    public static String[] FONT_LMONO = null;

    static {
        Properties properties = new Properties();

        InputStream propertiesInputStream = FontDescriptor.class.getResourceAsStream("/fonts.properties");
        if (propertiesInputStream != null) {
            try {
                properties.load(propertiesInputStream);
            } catch (IOException e) {
                throw new RuntimeException();
            }
        }

        FONT_LSERIF = new String[] {
                properties.getProperty("SerifRegular", "/home/hd/liberation-fonts/LiberationSerif-Regular.ttf"),
                properties.getProperty("SerifBold", "/home/hd/liberation-fonts/LiberationSerif-Bold.ttf"),
                properties.getProperty("SerifItalic", "/home/hd/liberation-fonts/LiberationSerif-Italic.ttf"),
                properties.getProperty("SerifBoldItalic", "/home/hd/liberation-fonts/LiberationSerif-BoldItalic.ttf")
        };

        FONT_LSANS = new String[] {
                properties.getProperty("SansRegular", "/home/hd/liberation-fonts/LiberationSerif-Regular.ttf"),
                properties.getProperty("SansBold", "/home/hd/liberation-fonts/LiberationSerif-Bold.ttf"),
                properties.getProperty("SansItalic", "/home/hd/liberation-fonts/LiberationSerif-Italic.ttf"),
                properties.getProperty("SansBoldItalic", "/home/hd/liberation-fonts/LiberationSerif-BoldItalic.ttf")
        };

        FONT_LMONO = new String[] {
                properties.getProperty("MonoRegular", "/home/hd/liberation-fonts/LiberationSerif-Regular.ttf"),
                properties.getProperty("MonoBold", "/home/hd/liberation-fonts/LiberationSerif-Bold.ttf"),
                properties.getProperty("MonoItalic", "/home/hd/liberation-fonts/LiberationSerif-Italic.ttf"),
                properties.getProperty("MonoBoldItalic", "/home/hd/liberation-fonts/LiberationSerif-BoldItalic.ttf")
        };
    }
}
