package ru.sgu.csit.inoc.deansoffice.services.impl;

import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.domain.Document;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.Report;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.TemplType;
import ru.sgu.csit.inoc.deansoffice.services.DocumentService;

import java.util.HashMap;
import java.util.Map;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:05
 */
//@Service
public class DocumentServiceImpl implements DocumentService , Report {
    //@ElementCollection(fetch = FetchType.EAGER)
    //@Transient
    public Map<String, Object> TEXT = new HashMap<String, Object>();
    protected Document document;

    public DocumentServiceImpl(Document document) {
        this.document = document;
    }

    public void clear() {
        TEXT.clear();
        TEXT.put("SSU_ONLY_HEADER", "САРАТОВСКИЙ ГОСУДАРСТВЕННЫЙ УНИВЕРСИТЕТ");
        TEXT.put("SSU_BY_HEADER", "ИМЕНИ Н. Г. ЧЕРНЫШЕВСКОГО");
        TEXT.put("SSU_ONLY", "Саратовский государственный университет");
        TEXT.put("SSU_ONLY_GEN", "Саратовского государственного университета");
        TEXT.put("SSU_BY", "имени Н. Г. Чернышевского");

        TEXT.put("SSU", "Саратовский государственный университет имени Н. Г. Чернышевского");
        TEXT.put("SSU_HEADER", "САРАТОВСКИЙ ГОСУДАРСТВЕННЫЙ УНИВЕРСИТЕТ ИМЕНИ Н. Г. ЧЕРНЫШЕВСКОГО");
    }

    @Override
    public Object getVariableValue(String variableName) {
        return TEXT.get(variableName);
    }

    @Override
    public TemplType getTemplateType() {
        return document.getPrintTemplate().getType();
    }

    @Override
    public String getTemplateFileName() {
        return document.getPrintTemplate().getFileName();
    }
}
