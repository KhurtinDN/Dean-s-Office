package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/27/11
 * Time: 2:02 PM
 */
public class StudentModel extends PersonModel {

    public SpecialityModel getSpeciality() {
        return get("speciality");
    }

    public void setSpeciality(SpecialityModel specialityModel) {
        set("speciality", specialityModel);
    }

    public GroupModel getGroup() {
        return get("group");
    }

    public void setGroup(GroupModel groupModel) {
        set("group", groupModel);
    }

    public String getStudentIdNumber() {
        return get("studentIdNumber");
    }

    public void setStudentIdNumber(String studentIdNumber) {
        set("studentIdNumber", studentIdNumber);
    }

    public String getDivision() {
        return get("division");
    }

    public void setDivision(String division) {
        set("division", division);
    }

    public String getStudyForm() {
        return get("studyForm");
    }

    public void setStudyForm(String studyForm) {
        set("studyForm", studyForm);
    }

}
