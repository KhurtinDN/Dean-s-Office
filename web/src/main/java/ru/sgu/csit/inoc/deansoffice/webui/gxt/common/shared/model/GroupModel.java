package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

/**
 * @author Denis Khurtin
 */
public class GroupModel extends DtoModel {
    private SpecialityModel marker;

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public Integer getCourse() {
        return get("course");
    }

    public void setCourse(Integer course) {
        set("course", course);
    }

    public SpecialityModel getSpeciality() {
        return get("speciality");
    }

    public void setSpeciality(SpecialityModel speciality) {
        set("speciality", speciality);
    }

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final GroupModel that = (GroupModel) model;

        return super.equals(that) &&
                ObjectUtil.equal(this.getName(), that.getName()) &&
                ObjectUtil.equal(this.getCourse(), that.getCourse()) &&
                ObjectUtil.equal(this.getSpeciality(), that.getSpeciality());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getName(),
                getCourse(),
                getSpeciality());
    }
}
