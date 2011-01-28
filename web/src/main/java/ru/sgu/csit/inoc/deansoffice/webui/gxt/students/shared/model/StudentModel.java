package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/27/11
 * Time: 2:02 PM
 */
public class StudentModel extends BaseModel {

    public StudentModel() {
    }

    public StudentModel(Long id, String name) {
        setId(id);
        setName(name);
    }

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
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
