package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import java.io.Serializable;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/27/11
 * Time: 2:02 PM
 */
@SuppressWarnings({"UnusedDeclaration"})
public class StudentModel extends PersonModel {
    private Division division;
    private StudyForm studyForm;

    public StudentModel() {
    }

    public StudentModel(Long id) {
        super(id);
    }

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
}
