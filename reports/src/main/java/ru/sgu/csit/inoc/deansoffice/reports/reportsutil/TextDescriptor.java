package ru.sgu.csit.inoc.deansoffice.reports.reportsutil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by XX (MesheryakovAV)
 * Date: 03.09.2010
 * Time: 12:07:40
 */
public class TextDescriptor {
    public final static int firstLineIndent = 40;

    public final static String[] FONT_LSERIF = {
            /*TextDescriptor.class.getResource("/liberation-fonts/LiberationSerif-Regular.ttf").getFile(),
            TextDescriptor.class.getResource("/liberation-fonts/LiberationSerif-Bold.ttf").getFile(),
            TextDescriptor.class.getResource("/liberation-fonts/LiberationSerif-Italic.ttf").getFile(),
            TextDescriptor.class.getResource("/liberation-fonts/LiberationSerif-BoldItalic.ttf").getFile()*/
            "/home/hd/liberation-fonts/LiberationSerif-Regular.ttf",
            "/home/hd/liberation-fonts/LiberationSerif-Bold.ttf",
            "/home/hd/liberation-fonts/LiberationSerif-Italic.ttf",
            "/home/hd/liberation-fonts/LiberationSerif-BoldItalic.ttf"
    };

    static {
        for (int i = 0; i < FONT_LSERIF.length; ++i) {
            FONT_LSERIF[i] = FONT_LSERIF[i].replace("%20", " ");
        }
    }
}
