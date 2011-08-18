package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

/**
 * @author Denis Khurtin
 */
public class EmployeeModel extends PersonModel {

    public String getDegree() {
        return get("degree");
    }

    public void setDegree(String degree) {
        set("degree", degree);
    }

    public String getPosition() {
        return get("position");
    }

    public void setPosition(String position) {
        set("position", position);
    }
}
