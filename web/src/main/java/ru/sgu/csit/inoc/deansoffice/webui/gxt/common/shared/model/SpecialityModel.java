package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

/**
 * @author Denis Khurtin
 */
public class SpecialityModel extends DtoModel {
    private FacultyModel marker;

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

    public String getCode() {
        return get("code");
    }

    public void setCode(String code) {
        set("code", code);
    }

    public FacultyModel getFaculty() {
        return get("faculty");
    }

    public void setFaculty(FacultyModel faculty) {
        set("faculty", faculty);
    }

    public Integer getCourseCount() {
        return get("courseCount");
    }

    public void setCourseCount(Integer courseCount) {
        set("courseCount", courseCount);
    }

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final SpecialityModel that = (SpecialityModel) model;

        return super.equals(that) &&
                ObjectUtil.equal(this.getFullName(), that.getFullName()) &&
                ObjectUtil.equal(this.getName(), that.getName()) &&
                ObjectUtil.equal(this.getCode(), that.getCode()) &&
                ObjectUtil.equal(this.getFaculty(), that.getFaculty()) &&
                ObjectUtil.equal(this.getCourseCount(), that.getFaculty());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getFullName(),
                getName(),
                getCode(),
                getFaculty(),
                getCourseCount());
    }
}
