package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

import java.io.Serializable;

/**
 * @author Denis Khurtin
 */
public class StudentModel extends PersonModel {
    private Division marker1;
    private StudyForm marker2;

    public String getGroupName() {
        return get("groupName");
    }

    public void setGroupName(String groupName) {
        set("groupName", groupName);
    }

    public String getSpecialityName() {
        return get("specialityName");
    }

    public void setSpecialityName(String specialityName) {
        set("specialityName", specialityName);
    }

    public Integer getCourse() {
        return get("course");
    }

    public void setCourse(Integer course) {
        set("course", course);
    }

    public String getStudentIdNumber() {
        return get("studentIdNumber");
    }

    public void setStudentIdNumber(String studentIdNumber) {
        set("studentIdNumber", studentIdNumber);
    }

    public Division getDivision() {
        return get("division");
    }

    public void setDivision(Division division) {
        set("division", division);
    }

    public StudyForm getStudyForm() {
        return get("studyForm");
    }

    public void setStudyForm(StudyForm studyForm) {
        set("studyForm", studyForm);
    }

    public Long getPhotoId() {
        return get("photoId");
    }

    public void setPhotoId(Long photoId) {
        set("photoId", photoId);
    }

    public enum Division implements Serializable { INTRAMURAL, EXTRAMURAL, EVENINGSTUDY }

    public enum StudyForm implements Serializable { BUDGET, COMMERCIAL }

    @Override
    public boolean equals(Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final StudentModel that = (StudentModel) model;

        return super.equals(that) &&
                ObjectUtil.equal(this.getGroupName(), that.getSpecialityName()) &&
                ObjectUtil.equal(this.getSpecialityName(), that.getSpecialityName()) &&
                ObjectUtil.equal(this.getCourse(), that.getCourse()) &&
                ObjectUtil.equal(this.getStudentIdNumber(), that.getStudentIdNumber()) &&
                ObjectUtil.equal(this.getDivision(), that.getDivision()) &&
                ObjectUtil.equal(this.getStudyForm(), that.getStudyForm()) &&
                ObjectUtil.equal(this.getPhotoId(), that.getPhotoId());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getGroupName(),
                getSpecialityName(),
                getCourse(),
                getStudentIdNumber(),
                getDivision(),
                getStudyForm(),
                getPhotoId());
    }
}
