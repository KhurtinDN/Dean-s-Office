package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

/**
 * @author Denis Khurtin
 */
public class AdministrationModel extends DtoModel {
    private String name;
    private EmployeeModel rector;

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public EmployeeModel getRector() {
        return get("rector");
    }

    public void setRector(EmployeeModel rector) {
        set("rector", rector);
    }
}
