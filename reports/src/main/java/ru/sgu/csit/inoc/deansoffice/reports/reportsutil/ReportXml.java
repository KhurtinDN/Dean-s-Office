package ru.sgu.csit.inoc.deansoffice.reports.reportsutil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 26.03.11
 * Time: 13:12
 */
public class ReportXml implements Report {
    private String templateFileName;
    private Map<String, Object> valuesMap = new HashMap<String, Object>();

    @Override
    public Object getVariableValue(String variableName) {
        return valuesMap.get(variableName);
    }

    @Override
    public TemplType getTemplateType() {
        return TemplType.XML;
    }

    @Override
    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }

    public Map<String, Object> getValuesMap() {
        return valuesMap;
    }

    public void setValuesMap(Map<String, Object> valuesMap) {
        this.valuesMap = valuesMap;
    }

    public void addValue(String key, Object value) {
        this.valuesMap.put(key, value);
    }
}
