package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.*;
import java.util.ArrayList;
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

    //@OneToOne(fetch = FetchType.LAZY)
    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private AdditionalStudentData additionalData;

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

    public AdditionalStudentData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(AdditionalStudentData additionalData) {
        this.additionalData = additionalData;
    }

    public enum Division {
        INTRAMURAL, EXTRAMURAL, EVENINGSTUDY
    }

    public enum StudyForm {
        BUDGET, COMMERCIAL
    }

    @Entity
    public static class AdditionalStudentData extends PersistentItem {
        //@OneToOne(fetch = FetchType.LAZY)
        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Photo photo;

        private Date birthday;
        private String birthPlace;
        private String education;
        private String workInfo;

        @ElementCollection(fetch = FetchType.EAGER)
        private List<Passport> passports;

        private String maritalStatus;
        private String childrenInfo;

        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Parent father;

        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Parent mather;

        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Address oldAddress;

        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Address actualAddress;

        public Photo getPhoto() {
            return photo;
        }

        public void setPhoto(Photo photo) {
            this.photo = photo;
        }

        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }

        public String getBirthPlace() {
            return birthPlace;
        }

        public void setBirthPlace(String birthPlace) {
            this.birthPlace = birthPlace;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(String education) {
            this.education = education;
        }

        public String getWorkInfo() {
            return workInfo;
        }

        public void setWorkInfo(String workInfo) {
            this.workInfo = workInfo;
        }

        @ElementCollection
        public List<Passport> getPassports() {
            return passports;
        }

        public void addPassport(Passport passport) {
            if (this.passports == null) {
                this.passports = new ArrayList<Passport>();
            }
            this.passports.add(passport);
        }

        public String getMaritalStatus() {
            return maritalStatus;
        }

        public void setMaritalStatus(String maritalStatus) {
            this.maritalStatus = maritalStatus;
        }

        public String getChildrenInfo() {
            return childrenInfo;
        }

        public void setChildrenInfo(String childrenInfo) {
            this.childrenInfo = childrenInfo;
        }

        public Parent getFather() {
            return father;
        }

        public void setFather(Parent father) {
            this.father = father;
        }

        public Parent getMather() {
            return mather;
        }

        public void setMather(Parent mather) {
            this.mather = mather;
        }

        public Address getOldAddress() {
            return oldAddress;
        }

        public void setOldAddress(Address oldAddress) {
            this.oldAddress = oldAddress;
        }

        public Address getActualAddress() {
            return actualAddress;
        }

        public void setActualAddress(Address actualAddress) {
            this.actualAddress = actualAddress;
        }
    }
}
