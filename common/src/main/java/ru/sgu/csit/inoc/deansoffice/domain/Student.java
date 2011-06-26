package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.*;

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
    
    private Integer course;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Group group;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Speciality speciality;

    private Division division;
    private StudyForm studyForm;
    private Role role;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private EnrollmentOrder enrollmentOrder;

    //@OneToOne(fetch = FetchType.LAZY)
    @OneToOne(cascade = CascadeType.ALL)
    private AdditionalStudentData additionalData;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<Stipend> stipends = new HashSet<Stipend>();

    public String getStudentIdNumber() {
        return studentIdNumber;
    }

    public void setStudentIdNumber(final String studentIdNumber) {
        this.studentIdNumber = studentIdNumber;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(final Integer course) {
        this.course = course;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(final Group group) {
        this.group = group;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(final Speciality speciality) {
        this.speciality = speciality;
    }

    public Division getDivision() {
        return division;
    }

    public void setDivision(final Division division) {
        this.division = division;
    }

    public StudyForm getStudyForm() {
        return studyForm;
    }

    public void setStudyForm(final StudyForm studyForm) {
        this.studyForm = studyForm;
    }

    public EnrollmentOrder getEnrollmentOrder() {
        return enrollmentOrder;
    }

    public void setEnrollmentOrder(final EnrollmentOrder enrollmentOrder) {
        this.enrollmentOrder = enrollmentOrder;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public AdditionalStudentData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(final AdditionalStudentData additionalData) {
        this.additionalData = additionalData;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public Set<Stipend> getStipends() {
        return stipends;
    }

    public void setStipends(final Set<Stipend> stipends) {
        this.stipends = stipends;
    }

    public void addStipend(final Stipend stipend) {
        stipends.add(stipend);
    }

    public enum Division {
        INTRAMURAL, EXTRAMURAL, EVENINGSTUDY
    }

    public enum StudyForm {
        BUDGET, COMMERCIAL
    }

    public enum Role {
        CAPTAIN
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Student)) {
            return false;
        }

        final Student that = (Student) o;

        return super.equals(o) &&
                Objects.equal(this.studentIdNumber, that.studentIdNumber) &&
                Objects.equal(this.course, that.course) &&
                Objects.equal(this.group, that.group) &&
                Objects.equal(this.speciality, that.speciality) &&
                Objects.equal(this.division, that.division) &&
                Objects.equal(this.studyForm, that.studyForm) &&
                Objects.equal(this.role, that.role) &&
                Objects.equal(this.enrollmentOrder, that.enrollmentOrder) &&
                Objects.equal(this.additionalData, that.additionalData) &&
                Objects.equal(this.stipends, that.stipends);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                studentIdNumber,
                course,
                group,
                speciality,
                division,
                studyForm,
                role,
                enrollmentOrder,
                additionalData,
                stipends);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("studentIdNumber", studentIdNumber)
                .add("course", course)
                .add("group", group)
                .add("speciality", speciality)
                .add("division", division)
                .add("studyForm", studyForm)
                .add("role", role)
                .add("enrollmentOrder", enrollmentOrder)
                .add("additionalData", additionalData)
                .add("stipends", stipends)
                .toString();
    }

    public static Student make(
            final Long id,
            final Date birthday,
            final Sex sex,
            final String firstName,
            final String middleName,
            final String lastName,
            final String firstNameDative,
            final String middleNameDative,
            final String lastNameDative,
            final Integer course,
            final Group group,
            final Speciality speciality,
            final Division division,
            final StudyForm studyForm,
            final Role role,
            final EnrollmentOrder enrollmentOrder,
            final AdditionalStudentData additionalStudentData,
            final Set<Stipend> stipends) {

        Student student = new Student();
        student.setId(id);
        student.setBirthday(birthday);
        student.setSex(sex);
        student.setFirstName(firstName);
        student.setMiddleName(middleName);
        student.setLastName(lastName);
        student.setFirstNameDative(firstNameDative);
        student.setMiddleNameDative(middleNameDative);
        student.setLastNameDative(lastNameDative);
        student.setCourse(course);
        student.setGroup(group);
        student.setSpeciality(speciality);
        student.setDivision(division);
        student.setStudyForm(studyForm);
        student.setRole(role);
        student.setEnrollmentOrder(enrollmentOrder);
        student.setAdditionalData(additionalStudentData);
        student.setStipends(stipends);

        return student;
    }

    @Entity
    public static class AdditionalStudentData extends PersistentItem {
        //@OneToOne(fetch = FetchType.LAZY)
        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Photo photo;

        private String birthPlace;
        private String education;
        private String workInfo;

//        @ElementCollection(fetch = FetchType.EAGER)
        @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
        @OrderColumn(name="passports_index")
        private List<Passport> passports = new ArrayList<Passport>();

        private String maritalStatus;
        private String childrenInfo;

        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Parent father;

        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Parent mother;

        private String oldAddress;

        private String actualAddress;

        public Photo getPhoto() {
            return photo;
        }

        public void setPhoto(final Photo photo) {
            this.photo = photo;
        }

        public String getBirthPlace() {
            return birthPlace;
        }

        public void setBirthPlace(final String birthPlace) {
            this.birthPlace = birthPlace;
        }

        public String getEducation() {
            return education;
        }

        public void setEducation(final String education) {
            this.education = education;
        }

        public String getWorkInfo() {
            return workInfo;
        }

        public void setWorkInfo(final String workInfo) {
            this.workInfo = workInfo;
        }

        @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
        public List<Passport> getPassports() {
            return passports;
        }

        public void setPassports(final List<Passport> passports) {
            this.passports = passports;
        }

        public void addPassport(final Passport passport) {
            if (this.passports == null) {
                this.passports = new ArrayList<Passport>();
            }
            this.passports.add(passport);
        }

        public String getMaritalStatus() {
            return maritalStatus;
        }

        public void setMaritalStatus(final String maritalStatus) {
            this.maritalStatus = maritalStatus;
        }

        public String getChildrenInfo() {
            return childrenInfo;
        }

        public void setChildrenInfo(final String childrenInfo) {
            this.childrenInfo = childrenInfo;
        }

        public Parent getFather() {
            return father;
        }

        public void setFather(final Parent father) {
            this.father = father;
        }

        public Parent getMother() {
            return mother;
        }

        public void setMother(final Parent mother) {
            this.mother = mother;
        }

        public String getOldAddress() {
            return oldAddress;
        }

        public void setOldAddress(final String oldAddress) {
            this.oldAddress = oldAddress;
        }

        public String getActualAddress() {
            return actualAddress;
        }

        public void setActualAddress(final String actualAddress) {
            this.actualAddress = actualAddress;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || !(o instanceof AdditionalStudentData)) {
                return false;
            }

            final AdditionalStudentData that = (AdditionalStudentData) o;

            return super.equals(o) &&
                    Objects.equal(this.photo, that.photo) &&
                    Objects.equal(this.birthPlace, that.birthPlace) &&
                    Objects.equal(this.education, that.education) &&
                    Objects.equal(this.workInfo, that.workInfo) &&
                    Objects.equal(this.passports, that.passports) &&
                    Objects.equal(this.maritalStatus, that.maritalStatus) &&
                    Objects.equal(this.childrenInfo, that.childrenInfo) &&
                    Objects.equal(this.father, that.father) &&
                    Objects.equal(this.mother, that.mother) &&
                    Objects.equal(this.oldAddress, that.oldAddress) &&
                    Objects.equal(this.actualAddress, that.actualAddress);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(
                    super.hashCode(),
                    photo,
                    birthPlace,
                    education,
                    workInfo,
                    passports,
                    maritalStatus,
                    childrenInfo,
                    father,
                    mother,
                    oldAddress,
                    actualAddress);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .addValue(super.toString())
                    .add("photo", photo)
                    .add("birthPlace", birthPlace)
                    .add("education", education)
                    .add("workInfo", workInfo)
                    .add("passports", passports)
                    .add("maritalStatus", maritalStatus)
                    .add("childrenInfo", childrenInfo)
                    .add("father", father)
                    .add("mother", mother)
                    .add("oldAddress", oldAddress)
                    .add("actualAddress", actualAddress)
                    .toString();
        }

        public static AdditionalStudentData make(
                final Long id,
                final Photo photo,
                final String birthPlace,
                final String education,
                final String workInfo,
                final List<Passport> passports,
                final String maritalStatus,
                final String childrenInfo,
                final Parent father,
                final Parent mother,
                final String oldAddress,
                final String actualAddress) {

            final AdditionalStudentData additionalStudentData = new AdditionalStudentData();
            additionalStudentData.setId(id);
            additionalStudentData.setPhoto(photo);
            additionalStudentData.setBirthPlace(birthPlace);
            additionalStudentData.setEducation(education);
            additionalStudentData.setWorkInfo(workInfo);
            additionalStudentData.setPassports(passports);
            additionalStudentData.setMaritalStatus(maritalStatus);
            additionalStudentData.setChildrenInfo(childrenInfo);
            additionalStudentData.setFather(father);
            additionalStudentData.setMother(mother);
            additionalStudentData.setOldAddress(oldAddress);
            additionalStudentData.setActualAddress(actualAddress);

            return additionalStudentData;
        }
    }
}
