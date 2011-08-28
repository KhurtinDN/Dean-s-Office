package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/10/11
 * Time: 12:42 PM
 */
public class FacultyModel extends DtoModel {
    private EmployeeModel marker;

    public String getFullName() {
        return get("fullName");
    }

    public void setFullName(String fullName) {
        set("fullName", fullName);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public EmployeeModel getDean() {
        return get("dean");
    }

    public void setDean(EmployeeModel dean) {
        set("dean", dean);
    }

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final FacultyModel that = (FacultyModel) model;

        return super.equals(that) &&
                ObjectUtil.equal(this.getFullName(), that.getFullName()) &&
                ObjectUtil.equal(this.getName(), that.getName()) &&
                ObjectUtil.equal(this.getDean(), that.getDean());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getFullName(),
                getName(),
                getDean());
    }
}
