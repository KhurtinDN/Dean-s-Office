package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

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

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final EmployeeModel that = (EmployeeModel) model;

        return super.equals(that) &&
                ObjectUtil.equal(this.getDegree(), that.getDegree()) &&
                ObjectUtil.equal(this.getPosition(), that.getPosition());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getDegree(),
                getPosition());
    }
}
