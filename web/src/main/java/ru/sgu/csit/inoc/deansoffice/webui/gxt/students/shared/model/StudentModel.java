package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

import java.io.Serializable;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/27/11
 * Time: 2:02 PM
 */
public class StudentModel extends PersonModel {
    private GroupModel group;
    private SpecialityModel speciality;
    private Division division;
    private StudyForm studyForm;

    public StudentModel() {
    }

    public StudentModel(Long id) {
        super(id);
    }

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel groupModel) {
        this.group = groupModel;
        set("group", groupModel);
    }

    public SpecialityModel getSpeciality() {
        return speciality;
    }

    public void setSpeciality(SpecialityModel specialityModel) {
        this.speciality = specialityModel;
        set("speciality", specialityModel);
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
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
        set("division", division);
    }

    public StudyForm getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(StudyForm studyForm) {
        this.studyForm = studyForm;
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
