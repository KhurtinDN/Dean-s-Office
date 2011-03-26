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
public class DocumentServiceImpl implements DocumentService {
    protected void putDefaultValues(Map<String, Object> valuesMap) {
        valuesMap.put("SSU_ONLY_HEADER", "САРАТОВСКИЙ ГОСУДАРСТВЕННЫЙ УНИВЕРСИТЕТ");
        valuesMap.put("SSU_BY_HEADER", "ИМЕНИ Н. Г. ЧЕРНЫШЕВСКОГО");
        valuesMap.put("SSU_ONLY", "Саратовский государственный университет");
        valuesMap.put("SSU_ONLY_GEN", "Саратовского государственного университета");
        valuesMap.put("SSU_BY", "имени Н. Г. Чернышевского");

        valuesMap.put("SSU", "Саратовский государственный университет имени Н. Г. Чернышевского");
        valuesMap.put("SSU_HEADER", "САРАТОВСКИЙ ГОСУДАРСТВЕННЫЙ УНИВЕРСИТЕТ ИМЕНИ Н. Г. ЧЕРНЫШЕВСКОГО");
    }
//    @Override
//    public Object getVariableValue(String variableName) {
//        return TEXT.get(variableName);
//    }
//
//    @Override
//    public TemplType getTemplateType() {
//        return document.getPrintTemplate().getType();
//    }
//
//    @Override
//    public String getTemplateFileName() {
//        return document.getPrintTemplate().getFileName();
//    }
}
