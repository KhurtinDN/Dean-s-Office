package ru.sgu.csit.inoc.deansoffice.reports.xml;

import com.lowagie.text.*;
import com.lowagie.text.Document;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Created by XX (MesheryakovAV)
 * Date: 03.09.2010
 * Time: 11:10:52
 */
public class XmlToPdfReportProcessorHandler extends DefaultHandler {
    private PdfWriter pdfWriter;
    private PrintWriter printWriter;

    private int elements;
    private int attributes;
    private int characters;
    private int ignorableWhitespace;
    private String url;
    private FontCollector fontCollector = new FontCollector();

    private Stack<Element> stackElements = new Stack<Element>();
    private Stack<MyTable> stackTables = new Stack<MyTable>();
    private Stack<MyCell> stackCells = new Stack<MyCell>();

    private Document document;
    private OutputStream outputStream = null;
    Report report;

    public XmlToPdfReportProcessorHandler(String url, Report report, OutputStream outputStream) {
        this.url = url;
        this.report = report;
        this.outputStream = outputStream;

        try {
            printWriter = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("The encoding is unsupported.", e);
        }
    }

    public void initDocument(String outputFileName, Rectangle pageSize) {
        document = new Document();
        if (outputFileName == null || outputFileName.isEmpty()) {
            outputFileName = "document.pdf";
        }
        try {
            if (outputStream == null) {
                outputStream = new FileOutputStream(outputFileName);
            }
            pdfWriter = PdfWriter.getInstance(document, outputStream);
        } catch (DocumentException e) {
            throw new RuntimeException("Document exception.", e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found.", e);
        }
        document.setPageSize(pageSize);
        document.open();
    }

    private Rectangle rectangleByName(String name) {
        Rectangle rect = PageSize.A4;

        if ("A0".equals(name)) {
            rect = PageSize.A0;
        } else if ("A0".equals(name)) {
            rect = PageSize.A0;
        } else if ("A1".equals(name)) {
            rect = PageSize.A1;
        } else if ("A2".equals(name)) {
            rect = PageSize.A2;
        } else if ("A3".equals(name)) {
            rect = PageSize.A3;
        } else if ("A4".equals(name)) {
            rect = PageSize.A4;
        } else if ("A5".equals(name)) {
            rect = PageSize.A5;
        } else if ("A6".equals(name)) {
            rect = PageSize.A6;
        } else if ("A7".equals(name)) {
            rect = PageSize.A7;
        } else if ("A8".equals(name)) {
            rect = PageSize.A8;
        } else if ("A9".equals(name)) {
            rect = PageSize.A9;
        } else if ("A10".equals(name)) {
            rect = PageSize.A10;
        } else if ("B0".equals(name)) {
            rect = PageSize.B0;
        } else if ("B1".equals(name)) {
            rect = PageSize.B1;
        } else if ("B2".equals(name)) {
            rect = PageSize.B2;
        } else if ("B3".equals(name)) {
            rect = PageSize.B3;
        } else if ("B4".equals(name)) {
            rect = PageSize.B4;
        } else if ("B5".equals(name)) {
            rect = PageSize.B5;
        } else if ("B6".equals(name)) {
            rect = PageSize.B6;
        } else if ("B7".equals(name)) {
            rect = PageSize.B7;
        } else if ("B8".equals(name)) {
            rect = PageSize.B8;
        } else if ("B9".equals(name)) {
            rect = PageSize.B9;
        } else if ("LETTER".equals(name)) {
            rect = PageSize.LETTER;
        }

        return rect;
    }

    private int alignElementHorizontalByName(String name) {
        if ("center".equals(name)) {
            return Element.ALIGN_CENTER;
        } else if ("left".equals(name)) {
            return Element.ALIGN_LEFT;
        } else if ("right".equals(name)) {
            return Element.ALIGN_RIGHT;
        } else if ("justified".equals(name)) {
            return Element.ALIGN_JUSTIFIED;
        }

        return Element.ALIGN_LEFT;
    }

    private int alignElementVerticalByName(String name) {
        if ("middle".equals(name)) {
            return Element.ALIGN_MIDDLE;
        } else if ("bottom".equals(name)) {
            return Element.ALIGN_BOTTOM;
        } else if ("top".equals(name)) {
            return Element.ALIGN_TOP;
        } else if ("baseline".equals(name)) {
            return Element.ALIGN_BASELINE;
        }

        return Element.ALIGN_TOP;
    }

    private void copyStateCell(PdfPCell targetCell, PdfPCell sourceCell) {
        targetCell.setHorizontalAlignment(sourceCell.getHorizontalAlignment());
        targetCell.setVerticalAlignment(sourceCell.getVerticalAlignment());
        targetCell.setColspan(sourceCell.getColspan());
        targetCell.setPaddingBottom(sourceCell.getPaddingBottom());
        targetCell.setPaddingLeft(sourceCell.getPaddingLeft());
        targetCell.setPaddingRight(sourceCell.getPaddingRight());
        targetCell.setPaddingTop(sourceCell.getPaddingTop());
        targetCell.setFixedHeight(sourceCell.getFixedHeight());
        targetCell.setMinimumHeight(sourceCell.getMinimumHeight());
        targetCell.setBorder(sourceCell.getBorder());
        targetCell.setBorderWidth(sourceCell.getBorderWidth());
        targetCell.setRotation(sourceCell.getRotation());
    }

    //=======================================================
    // Обработчики событий. Методы интерфейса DefaultHandler
    //=======================================================

    // Начало документа
    public void startDocument() {
        ;
    }

    // Конец документа
    public void endDocument() {
        //out.Flush();
        document.close();
        pdfWriter.close();
        printInfo();
        printWriter.flush();
    }

    // Встретился открывающий тэг элемента
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        elements++;
        if (attributes != null) {
            this.attributes += attributes.getLength();
        }
        if ("document".equals(qName)) {
            String outputFileName = null;
            boolean pdfFlag = false;
            Rectangle format = PageSize.A4;
            String orientation = "portrait";

            if (attributes != null) {
                for (int i = 0, n = attributes.getLength(); i < n; ++i) {
                    String attributeName = attributes.getLocalName(i);
                    String attributeValue = attributes.getValue(i);

                    if (attributeValue.length() > 0) {
                        if ("type".equals(attributeName)) {
                            if ("PDF".equals(attributeValue)) {
                                pdfFlag = true;
                            }
                        } else if ("name".equals(attributeName)) {
                            outputFileName = attributeValue;
                        } else if ("format".equals(attributeName)) {
                            format = rectangleByName(attributeValue);
                        } else if ("orientation".equals(attributeName)) {
                            orientation = attributeValue;
                        }
                    }
                }
            }
            if (!pdfFlag) {
                throw new RuntimeException("Document [" + url + "] is not contains definition PDF type.");
            }
            if ("landscape".equals(orientation)) {
                format = format.rotate();
            }
            initDocument(outputFileName, format);
        } else if ("var".equals(qName)) {
            if (attributes != null) {
                for (int i = 0, n = attributes.getLength(); i < n; ++i) {
                    String attributeName = attributes.getLocalName(i);
                    String attributeValue = attributes.getValue(i);

                    if ("name".equals(attributeName)) {
                        String strChunk = report.getVariableValue(attributeValue);

                        if (!stackElements.isEmpty()) {
                            ((Phrase) stackElements.peek()).add(new Chunk(strChunk, fontCollector.getCurrentFont()));
                        }
                    }
                }
            }
        } else if ("var".equals(qName)) {
            if (attributes != null) {
                for (int i = 0, n = attributes.getLength(); i < n; ++i) {
                    String attributeName = attributes.getLocalName(i);
                    String attributeValue = attributes.getValue(i);

                    if ("name".equals(attributeName)) {
                        String strChunk = report.getVariableValue(attributeValue);

                        if (!stackElements.isEmpty()) {
                            ((Phrase) stackElements.peek()).add(new Chunk(strChunk, fontCollector.getCurrentFont()));
                        }
                    }
                }
            }
        } else if ("image".equals(qName)) {
            String imgFileName = null;
            String imgFormat = "jpg";

            if (attributes != null) {
                for (int i = 0, n = attributes.getLength(); i < n; ++i) {
                    String attributeName = attributes.getLocalName(i);
                    String attributeValue = attributes.getValue(i);

                    if (attributeValue.length() > 0) {
                        if ("src".equals(attributeName)) {
                            imgFileName = attributeValue;
                        } else if ("format".equals(attributeName)) {
                            imgFormat = attributeValue;
                        }
                    }
                }
            }
            if (imgFileName != null) {
                Image image;

                try {
                    image = Image.getInstance(imgFileName);
                } catch (BadElementException e) {
                    throw new RuntimeException("Image bad element.", e);
                } catch (IOException e) {
                    throw new RuntimeException("Image file " + imgFileName + " not found.", e);
                }
                //image.scaleAbsoluteWidth(20);
                stackElements.push(image);
            }
        } else if ("p".equals(qName)) {
            Paragraph paragraph = new Paragraph();

            if (attributes != null) {
                for (int i = 0, n = attributes.getLength(); i < n; ++i) {
                    String attributeName = attributes.getLocalName(i);
                    String attributeValue = attributes.getValue(i);

                    if (attributeValue.length() > 0) {
                        if ("align".equals(attributeName)) {
                            paragraph.setAlignment(alignElementHorizontalByName(attributeValue));
                        } else if ("spacingAfter".equals(attributeName)) {
                            paragraph.setSpacingAfter(Float.valueOf(attributeValue));
                        } else if ("spacingBefore".equals(attributeName)) {
                            paragraph.setSpacingBefore(Float.valueOf(attributeValue));
                        }
                    }
                }
            }

            stackElements.push(paragraph);
            stackElements.push(new Phrase());
        } else if ("table".equals(qName)) {
            PdfPTable table = null;
            MyTable myTable = new MyTable();

            if (attributes != null) {
                for (int i = 0, n = attributes.getLength(); i < n; ++i) {
                    String attributeName = attributes.getLocalName(i);
                    String attributeValue = attributes.getValue(i);

                    if (attributeValue.length() > 0) {
                        if ("columns".equals(attributeName)) {
                            table = new PdfPTable(Integer.valueOf(attributeValue));
                        } else if ("widths".equals(attributeName)) {
                            ArrayList<Float> arrayWidths = new ArrayList<Float>();
                            Scanner scanner = new Scanner(attributeValue);

                            while (scanner.hasNext()) {
                                arrayWidths.add(Float.valueOf(scanner.next()));
                            }

                            float[] widths = new float[arrayWidths.size()];

                            for (int j = 0; j < widths.length; ++j) {
                                widths[j] = arrayWidths.get(j);
                            }
                            table = new PdfPTable(widths);
                        } else if (table != null) {
                            if ("width".equals(attributeName)) {
                                table.setWidthPercentage(Float.valueOf(attributeValue));
                            } else if ("align".equals(attributeName)) {
                                table.setHorizontalAlignment(alignElementHorizontalByName(attributeValue));
                            } else if ("border".equals(attributeName)) {
                                myTable.border = Integer.valueOf(attributeValue);
                            } else if ("borderWidth".equals(attributeName)) {
                                myTable.borderWidth = Float.valueOf(attributeValue);
                            }
                        }
                    }
                }
            }
            if (table == null) {
                throw new RuntimeException("Table is not contains column?..");
            }
            myTable.table = table;
            stackTables.push(myTable);
            if (!stackCells.isEmpty()) {
                PdfPCell oldCell = stackCells.pop().cell;
                PdfPCell newCell = new PdfPCell(table);

                copyStateCell(newCell, oldCell);

                stackCells.push(new MyCell(newCell, true));
            }
        } else if ("td".equals(qName)) {
            PdfPCell cell = new PdfPCell();

            cell.setBorder(stackTables.peek().border);
            cell.setBorderWidth(stackTables.peek().borderWidth);
            if (attributes != null) {
                for (int i = 0, n = attributes.getLength(); i < n; ++i) {
                    String attributeName = attributes.getLocalName(i);
                    String attributeValue = attributes.getValue(i);

                    if (attributeValue.length() > 0) {
                        if ("align".equals(attributeName)) {
                            cell.setHorizontalAlignment(alignElementHorizontalByName(attributeValue));
                        } else if ("valign".equals(attributeName)) {
                            cell.setVerticalAlignment(alignElementVerticalByName(attributeValue));
                        } else if ("colspan".equals(attributeName)) {
                            cell.setColspan(Integer.valueOf(attributeValue));
                        } else if ("padding".equals(attributeName)) {
                            cell.setPadding(Float.valueOf(attributeValue));
                        } else if ("paddingBottom".equals(attributeName)) {
                            cell.setPaddingBottom(Float.valueOf(attributeValue));
                        } else if ("paddingLeft".equals(attributeName)) {
                            cell.setPaddingLeft(Float.valueOf(attributeValue));
                        } else if ("paddingRight".equals(attributeName)) {
                            cell.setPaddingRight(Float.valueOf(attributeValue));
                        } else if ("paddingTop".equals(attributeName)) {
                            cell.setPaddingTop(Float.valueOf(attributeValue));
                        } else if ("fixedHeight".equals(attributeName)) {
                            cell.setFixedHeight(Float.valueOf(attributeValue));
                        } else if ("minimumHeight".equals(attributeName)) {
                            cell.setMinimumHeight(Float.valueOf(attributeValue));
                        } else if ("border".equals(attributeName)) {
                            cell.setBorder(Integer.valueOf(attributeValue));
                        } else if ("borderWidth".equals(attributeName)) {
                            cell.setBorderWidth(Float.valueOf(attributeValue));
                        }
                        else if ("rotation".equals(attributeName)) {
                            cell.setRotation(Integer.valueOf(attributeValue));
                        }
                    }
                }
            }

            stackCells.push(new MyCell(cell));
            stackElements.push(new Phrase());
        } else if ("b".equals(qName)) {
            Font font = fontCollector.getCurrentFont();
            int style = fontCollector.getCurrentStyle();

            fontCollector.setNewFont(font.getSize(), style | Font.BOLD);
        } else if ("i".equals(qName)) {
            Font font = fontCollector.getCurrentFont();
            int style = fontCollector.getCurrentStyle();

            fontCollector.setNewFont(font.getSize(), style | Font.ITALIC);
        } else if ("u".equals(qName)) {
            Font font = fontCollector.getCurrentFont();
            int style = fontCollector.getCurrentStyle();

            fontCollector.setNewFont(font.getSize(), style | Font.UNDERLINE);
        } else if ("font".equals(qName)) {
            Font font = fontCollector.getCurrentFont();
            Float size = font.getSize();
            int style = fontCollector.getCurrentStyle();
            boolean setStyle = false;

            if (attributes != null) {
                for (int i = 0, n = attributes.getLength(); i < n; ++i) {
                    String attributeName = attributes.getLocalName(i);
                    String attributeValue = attributes.getValue(i);

                    if ("size".equals(attributeName)) {
                        size = Float.valueOf(attributeValue);
                    } else if ("style".equals(attributeName)) {
                        if (!setStyle) {
                            style = 0;
                            setStyle = true;
                        }
                        if ("bold".equals(attributeValue)) {
                            style |= Font.BOLD;
                        } else if ("italic".equals(attributeValue)) {
                            style |= Font.ITALIC;
                        } else if ("underline".equals(attributeValue)) {
                            style |= Font.UNDERLINE;
                        }
                    }
                }
            }

            fontCollector.setNewFont(size, style);
        }
    }

    // Встретился закрывающий тэг элемента
    public void endElement(String uri, String localName, String qName) {
        if ("image".equals(qName)) {
            try {
                Image image = (Image) stackElements.pop();
                Paragraph paragraph = new Paragraph();

                paragraph.add(new Chunk(image, 0, 0));
                if (stackCells.isEmpty()) {
                    document.add(paragraph);
                } else {
                    if (!stackCells.peek().containsTable) {
                        ((Phrase) stackElements.peek()).add(paragraph);
                    }
                }
                //document.add(paragraph);
            } catch (Exception e) {
                throw new RuntimeException("EndElement error.", e);
            }
        } if ("p".equals(qName)) {
            try {
                Phrase phrase = (Phrase) stackElements.pop();
                Paragraph paragraph = (Paragraph) stackElements.pop();

                paragraph.add(phrase);
                if (stackCells.isEmpty()) {
                    document.add(paragraph);
               } else {
                    if (!stackCells.peek().containsTable) {
                        ((Phrase) stackElements.peek()).add(paragraph);
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("EndElement error.", e);
            }
        } if ("table".equals(qName)) {
            try {
                PdfPTable table = stackTables.pop().table;

                if (stackTables.isEmpty()) {
                    document.add(table);
                }
            } catch (Exception e) {
                throw new RuntimeException("EndElement error.", e);
            }
        } if ("td".equals(qName)) {
            Phrase phrase = (Phrase) stackElements.pop();

            if (!stackTables.isEmpty()) {
                MyCell myCell = stackCells.pop();

                if (!myCell.containsTable) {
                    PdfPCell cell = new PdfPCell(phrase);

                    copyStateCell(cell, myCell.cell);
                    myCell.cell = cell;
                }
                stackTables.peek().table.addCell(myCell.cell);
            }
        } else if ("b".equals(qName)
                || "i".equals(qName)
                || "u".equals(qName)
                || "font".equals(qName)) {
            fontCollector.resetFont();
        }
    }

    // Текстовые символы
    public void characters(char ch[], int start, int length) {
        if (!stackElements.isEmpty()) {
            String strChunk = new String(ch, start, length);

            ((Phrase) stackElements.peek()).add(new Chunk(strChunk, fontCollector.getCurrentFont()));
        }
        characters += length;
    }

    // Необрабатываемые символы(например, содержимое секции CDATA)
    public void ignorableWhitespace(char ch[], int start, int length) {
        characters(ch, start, length);
    }

    // Инструкции XML-процессору
    public void processingInstruction(String target, String data) {
        printWriter.print("<?");
        printWriter.print(target);
        if (data != null && data.length() > 0) {
            printWriter.print(' ');
            printWriter.print(data);
        }
        printWriter.print("?>");
    }

    //===================================================
    // Методы интерфейса ErrorHandler
    //===================================================
    // Последнее предупреждение
    public void warning(SAXParseException ex) {
        System.err.println("Warning at " +
                ex.getLineNumber() + " . " +
                ex.getColumnNumber() + "  -  " +
                ex.getMessage());
    }

    // Произошла ошибка
    public void error(SAXParseException ex) {
        System.err.println("Error at {" +
                ex.getLineNumber() + " . " +
                ex.getColumnNumber() + "  -  " +
                ex.getMessage());
    }

    // Такие ошибки исправить уже нельзя
    public void fatalError(SAXParseException ex) throws SAXException {
        System.err.println("Fatal error at {" +
                ex.getLineNumber() + " . " +
                ex.getColumnNumber() + "  -  " +
                ex.getMessage());
        throw ex;
    }

    //=======================================================
    // Вывести информацию о документе
    //=======================================================
    public void printInfo() {
        System.out.println();

        System.out.println("Документ " + url + " был успешно обработан");

        System.out.println("Элементов : " + elements);
        System.out.println("Атрибутов : " + attributes);
        System.out.println("Символов  : " + characters);
    }
}
