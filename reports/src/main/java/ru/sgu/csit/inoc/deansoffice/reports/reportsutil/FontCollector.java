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
                    BaseFont.createFont(FontDescriptor.FONT_LSERIF[0], BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            currentFont = new MyFont(new Font(baseFont, Font.DEFAULTSIZE, Font.NORMAL), 0, FontDescriptor.FONT_LSERIF);
        } catch (DocumentException e) {
            throw new RuntimeException("Document exception.", e);
        } catch (IOException e) {
            throw new RuntimeException("IO exception.", e);
        }
    }

    public Font setNewFont(float size, int style, String[] arrayFontFileNames) {
        if (arrayFontFileNames == null) {
            arrayFontFileNames = FontDescriptor.FONT_LSERIF;
        }
        stackFonts.push(currentFont);
        try {
            BaseFont baseFont = BaseFont.createFont(arrayFontFileNames[style & 3],
                    BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
            currentFont = new MyFont(new Font(baseFont, size, Font.UNDERLINE & style), style, arrayFontFileNames);
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

    public String[] getCurrentFontFileNames() {
        return currentFont.fontFileNames;
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
        public String[] fontFileNames;

        public MyFont() {
        }

        public MyFont(Font font, int style, String[] fontFileNames) {
            this.font = font;
            this.style = style;
            this.fontFileNames = fontFileNames;
        }
    }
}
