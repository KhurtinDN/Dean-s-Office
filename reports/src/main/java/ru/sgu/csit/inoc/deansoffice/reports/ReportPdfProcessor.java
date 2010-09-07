package ru.sgu.csit.inoc.deansoffice.reports;

import org.xml.sax.SAXException;
import ru.sgu.csit.inoc.deansoffice.domain.Document;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.reports.xml.XmlToPdfReportProcessorHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created by XX (MesheryakovAV)
 *   Date: 03.09.2010
 *   Time: 10:43:52
 */
public enum ReportPdfProcessor {
    INSTANCE;

    public static ReportPdfProcessor getInstance() {
        return INSTANCE;
    }

    private void generateReportFromXml(Document document, Student student) {
        SAXParserFactory factory = SAXParserFactory.newInstance();

        factory.setNamespaceAware(false); // Выключить пространство имен
        try {
            /*SchemaFactory shemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

            factory.setSchema(shemaFactory.newSchema(new File("document.xsd")));*/
            SAXParser parser = factory.newSAXParser();
            String fileName = document.getPrintTemplate().getFileName();

            parser.parse(new File(fileName), new XmlToPdfReportProcessorHandler(fileName, document));
        } catch (ParserConfigurationException e) {
            throw new RuntimeException("Parser configuration exception.", e);
        } catch (SAXException e) {
            throw new RuntimeException("SAX exception.", e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generate(Document document, Student student) {
        switch (document.getPrintTemplate().getType()) {
            case XML:
                generateReportFromXml(document, student);
                break;
            default:
                break;
        }
    }
}