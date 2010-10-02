package ru.sgu.csit.inoc.deansoffice.shared.dto;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 2, 2010
 * Time: 12:51:17 PM
 */
public class StudentDto implements java.io.Serializable {
    private Long id;
    private String name;
    private String studentIdNumber;
    private String division;
    private String studyForm;

    public StudentDto() {
    }

    public StudentDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(String studyForm) {
        this.studyForm = studyForm;
    }
}
