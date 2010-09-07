package ru.sgu.csit.inoc.deansoffice.reports.reportsutil;

import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.BaseFont;

import java.io.IOException;
import java.util.Stack;

/**
 * Created by XX (MesheryakovAV)
 * Date: 03.09.2010
 * Time: 11:19:02
 */
public class FontCollector {
    private MyFont currentFont;
    private Stack<MyFont> stackFonts = new Stack<MyFont>();

    public FontCollector() {
        setFontBase();
    }

    private void setFontBase() {
        try {
            BaseFont baseFont =
                    BaseFont.createFont(TextDescriptor.FONT_LSERIF[0], BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            currentFont = new MyFont(new Font(baseFont, Font.DEFAULTSIZE, Font.NORMAL), 0);
        } catch (DocumentException e) {
            throw new RuntimeException("Document exception.", e);
        } catch (IOException e) {
            throw new RuntimeException("IO exception.", e);
        }
    }

    public Font setNewFont(float size, int style) {
        stackFonts.push(currentFont);
        try {
            BaseFont baseFont = BaseFont.createFont(TextDescriptor.FONT_LSERIF[style & 3],
                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            currentFont = new MyFont(new Font(baseFont, size, Font.UNDERLINE & style), style);
        } catch (DocumentException e) {
            throw new RuntimeException("Document exception.", e);
        } catch (IOException e) {
            throw new RuntimeException("IO exception.", e);
        }

        return currentFont.font;
    }

    public Font getCurrentFont() {
        return currentFont.font;
    }

    public int getCurrentStyle() {
        return currentFont.style;
    }

    public void resetFont() {
        if (!stackFonts.isEmpty()) {
            currentFont = stackFonts.pop();
        } else {
            setFontBase();
        }
    }

    private class MyFont {
        public Font font;
        public int style;

        public MyFont() {
        }

        public MyFont(Font font, int style) {
            this.font = font;
            this.style = style;
        }
    }
}
