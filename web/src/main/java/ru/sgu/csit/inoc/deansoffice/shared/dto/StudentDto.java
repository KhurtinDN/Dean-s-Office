package ru.sgu.csit.inoc.deansoffice.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 2, 2010
 * Time: 12:51:17 PM
 */
public class StudentDto extends BaseModel {
    public StudentDto() {
    }

    public StudentDto(Long id, String name) {
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
