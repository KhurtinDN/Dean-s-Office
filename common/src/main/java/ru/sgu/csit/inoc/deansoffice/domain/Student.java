package ru.sgu.csit.inoc.deansoffice.domain;

import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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
    
    private Integer cource;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Group group;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Speciality speciality;

    private Division division;
    private StudyForm studyForm;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private EnrollmentOrder enrollmentOrder;

    @OneToOne(fetch = FetchType.LAZY)
    private AdditionalData additionalData;

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    public Integer getCource() {
        return cource;
    }

    public void setCource(Integer cource) {
        this.cource = cource;
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

    public Division getDivision() {
        return division;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public StudyForm getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(StudyForm studyForm) {
        this.studyForm = studyForm;
    }

    public EnrollmentOrder getEnrollmentOrder() {
        return enrollmentOrder;
    }

    public void setEnrollmentOrder(EnrollmentOrder enrollmentOrder) {
        this.enrollmentOrder = enrollmentOrder;
    }

    public enum Division {
        INTRAMURAL, EXTRAMURAL, EVENINGSTUDY
    }

    public enum StudyForm {
        BUDGET, COMMERCIAL
    }

    @Entity
    public static class AdditionalData extends PersistentItem {
        @OneToOne(fetch = FetchType.LAZY)
        private Photo photo;

        private Date birthday;
        private String birthPlace;
        private String citizenship;
        private String education;
        private String workInfo;

        private List<Passport> passports;

        private String maritalStatus;
        private String childrenInfo;

        private Parent father;
        private Parent mather;
    }
}
