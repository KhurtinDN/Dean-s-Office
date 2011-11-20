package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.*;

/**
 * The student.
 */
@Entity
@Table(name = "student")
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

    private Division division;
    private StudyForm studyForm;
    private Role role;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private EnrollmentOrder enrollmentOrder;

    private Date releaseDate;

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

    public Role getRole() {
        return role;
    }

    public void setRole(final Role role) {
        this.role = role;
    }

    public void setEnrollmentOrder(final EnrollmentOrder enrollmentOrder) {
        this.enrollmentOrder = enrollmentOrder;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @OneToOne(cascade = CascadeType.ALL)
    public AdditionalStudentData getAdditionalData() {
        return additionalData;
    }

    public void setAdditionalData(final AdditionalStudentData additionalData) {
        this.additionalData = additionalData;
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
                Objects.equal(this.group, that.group) &&
                Objects.equal(this.speciality, that.speciality) &&
                Objects.equal(this.division, that.division) &&
                Objects.equal(this.studyForm, that.studyForm) &&
                Objects.equal(this.role, that.role) &&
                Objects.equal(this.enrollmentOrder, that.enrollmentOrder) &&
                Objects.equal(this.releaseDate, that.releaseDate) &&
                Objects.equal(this.additionalData, that.additionalData) &&
                Objects.equal(this.stipends, that.stipends);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                studentIdNumber,
                group,
                speciality,
                division,
                studyForm,
                role,
                enrollmentOrder,
                releaseDate,
                additionalData,
                stipends);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("studentIdNumber", studentIdNumber)
                .add("group", group)
                .add("speciality", speciality)
                .add("division", division)
                .add("studyForm", studyForm)
                .add("role", role)
                .add("enrollmentOrder", enrollmentOrder)
                .add("releaseDate", releaseDate)
                .add("additionalData", additionalData)
                .add("stipends", stipends)
                .toString();
    }

    @Entity
    @Table(name = "additional_student_data")
    public static class AdditionalStudentData extends PersistentItem {
        @ManyToOne(cascade = CascadeType.MERGE)
        @PrimaryKeyJoinColumn
        private Photo photo;

        private String birthPlace;
        private String education;
        private String workInfo;

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
    }
}
