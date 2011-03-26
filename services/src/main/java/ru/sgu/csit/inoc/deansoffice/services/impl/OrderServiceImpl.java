package ru.sgu.csit.inoc.deansoffice.services.impl;

import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.reports.ReportPdfProcessor;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.Report;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.ReportXml;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.Templater;
import ru.sgu.csit.inoc.deansoffice.services.DirectiveService;
import ru.sgu.csit.inoc.deansoffice.services.OrderService;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 09.03.11
 * Time: 12:10
 */
//@Service
public class OrderServiceImpl extends DocumentServiceImpl implements OrderService {
//    @Autowired
//    private DirectiveService directiveService;

    private Map rootMap = new HashMap();

    private void build(ReportXml report, Order order, Faculty faculty) {
        putDefaultValues(report.getValuesMap());
        report.addValue("FACULTY_FULLNAME", faculty.getFullName());
        report.addValue("FACULTY_SHORTNAME", faculty.getShortName());

        String note = null;
        try {
            note = order.getData().getNote();
        } catch (NullPointerException e) { // если нет примечания, то ничего не делаем
        }
        report.addValue("ORDER_NOTE", note != null ? ("[" + note + "]") : "");
        Leader supervisor = order.getData().getSupervisor();
        report.addValue("SUPERVISOR_POSITION", supervisor.getPosition());
        report.addValue("SUPERVISOR_DEGREE", supervisor.getDegree());
        report.addValue("SUPERVISOR_NAME", supervisor.generateShortName(true));
        String coordinators = "";
        for (Coordinator coordinator : order.getData().getCoordinators()) {
            coordinators += coordinator.getPosition() + "\n";
        }
        report.addValue("COORDINATORS_LIST", coordinators);

        for (Directive directive : order.getDirectives()) {
            try {
                directive.getData().setBody(new DirectiveServiceImpl().generatePrintTemplateBody(directive));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        rootMap.put("directives", order.getDirectives());
    }

    @Override
    public Map getRootMap() {
        return rootMap;
    }

    @Override
    public void generatePrintForm(Order order, Faculty faculty, OutputStream outputStream) {
        ReportXml reportXml = new ReportXml();

        if (order.getPrintTemplate() == null ||
                order.getPrintTemplate().getFileName() == null) {
            setDefaultPrintTemplate(order);
        }
        reportXml.setTemplateFileName(order.getPrintTemplate().getFileName());
        build(reportXml, order, faculty);
        String templaterTemplName = reportXml.getTemplateFileName().substring(
                reportXml.getTemplateFileName().lastIndexOf("/") + 1,
                reportXml.getTemplateFileName().lastIndexOf(".xml"));
        String templDir = reportXml.getTemplateFileName().substring(0,
                reportXml.getTemplateFileName().lastIndexOf("/"));
        try {
            Templater templater = new Templater(templDir);
            templater.process(templaterTemplName, getRootMap(),
                    new FileOutputStream(new File(reportXml.getTemplateFileName())));
        } catch (IOException e) {
            throw new RuntimeException("IO Exception in templater.", e);
        } catch (TemplateException e) {
            throw new RuntimeException("Template Exception in templater.", e);
        }
        ReportPdfProcessor.getInstance().generate(reportXml, outputStream);
    }

    @Override
    public void setDefaultPrintTemplate(Order order) {
        String documentName = "order.ftl";
        String templName = ReferenceServiceImpl.class.getResource("/templates/" + documentName).getFile();
        templName = templName.replace("%20", " ");
        templName = templName + ".xml";
        order.setPrintTemplate(new Template(templName));
    }
}
