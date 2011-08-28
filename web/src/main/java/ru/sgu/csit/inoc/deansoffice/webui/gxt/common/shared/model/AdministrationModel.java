package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

/**
 * @author Denis Khurtin
 */
public class AdministrationModel extends DtoModel {
    private EmployeeModel marker;

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

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || !getClass().equals(model.getClass())) {
            return false;
        }

        final AdministrationModel that = (AdministrationModel) model;

        return super.equals(model) &&
                ObjectUtil.equal(this.getName(), that.getName()) &&
                ObjectUtil.equal(this.getRector(), that.getRector());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getName(),
                getRector());
    }
}
