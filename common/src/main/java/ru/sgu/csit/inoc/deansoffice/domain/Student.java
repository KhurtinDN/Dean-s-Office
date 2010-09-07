package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.*;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Group group;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Speciality speciality;

    private StudyForm studyForm;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
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
