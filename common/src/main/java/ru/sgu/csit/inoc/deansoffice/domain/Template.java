package ru.sgu.csit.inoc.deansoffice.domain;

/**
 * Created by XX (MesheryakovAV)
 * Date: 03.09.2010
 * Time: 10:51:53
 */
public class Template {
    private String fileName;
    private TemplType type = TemplType.XML;

    public Template() {
    }

    public Template(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public TemplType getType() {
        return type;
    }

    public void setType(TemplType type) {
        this.type = type;
    }

    public static enum TemplType {
        XML
    }
}
