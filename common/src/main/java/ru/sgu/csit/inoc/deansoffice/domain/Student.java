package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 11:28:40 AM
 */
@Entity
public class Student extends Person {
    /**
     *  This is number of student ticket.
     */
    private String studentIdNumber;

    private Group group;
    private Speciality speciality;
    private StudyForm studyForm;
    private Order enrollmentOrder;

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public StudyForm getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(StudyForm studyForm) {
        this.studyForm = studyForm;
    }

    public Order getEnrollmentOrder() {
        return enrollmentOrder;
    }

    public void setEnrollmentOrder(Order enrollmentOrder) {
        this.enrollmentOrder = enrollmentOrder;
    }

    enum StudyForm {
        INTRAMURAL, EXTRAMURAL
    }
}
