package ru.sgu.csit.inoc.deansoffice.reports.reportsutil;

/**
 * Created by XX (MesheryakovAV)
 * Date: 28.09.2010
 * Time: 13:29:38
 */
public interface Report {
    Object getVariableValue(String variableName);
    TemplType getTemplateType();
    String getTemplateFileName();
}
