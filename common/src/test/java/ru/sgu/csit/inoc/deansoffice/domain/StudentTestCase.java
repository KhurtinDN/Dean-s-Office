package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertFalse;
import static ru.sgu.csit.inoc.deansoffice.CommonTestUtils.generateId;
import static ru.sgu.csit.inoc.deansoffice.CommonTestUtils.testWhenObjectsAreDifferent;
import static ru.sgu.csit.inoc.deansoffice.CommonTestUtils.testWhenObjectsAreEqual;
import static ru.sgu.csit.inoc.deansoffice.DomainTestUtils.makeStudent;

public class StudentTestCase {

    @Test
    public void testEqualsWhenOtherHasDifferentClass() {

        // INIT
        Student testee = new Student();

        // ACT
        boolean result = testee.equals(new Object());

        // CHECK
        assertFalse("result", result);
    }

    @Test
    public void testEqualsWhenContentDiffersInId() {
        Student testee1 = new Student();
        testee1.setId(generateId());

        Student testee2 = new Student();
        testee2.setId(generateId());

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInBirthday() {
        Date birthday1 = new Date(generateId());
        Date birthday2 = new Date(generateId());

        Student testee1 = new Student();
        testee1.setBirthday(birthday1);

        Student testee2 = new Student();
        testee2.setBirthday(birthday2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInSex() {
        Student testee1 = new Student();
        testee1.setSex(Person.Sex.MALE);

        Student testee2 = new Student();
        testee2.setSex(Person.Sex.FEMALE);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInLastName() {
        Student testee1 = new Student();
        testee1.setLastName("name1");

        Student testee2 = new Student();
        testee2.setLastName("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInFirstName() {
        Student testee1 = new Student();
        testee1.setFirstName("name1");

        Student testee2 = new Student();
        testee2.setFirstName("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInMiddleName() {
        Student testee1 = new Student();
        testee1.setMiddleName("name1");

        Student testee2 = new Student();
        testee2.setMiddleName("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInLastNameDative() {
        Student testee1 = new Student();
        testee1.setLastNameDative("name1");

        Student testee2 = new Student();
        testee2.setLastNameDative("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInFirstNameDative() {
        Student testee1 = new Student();
        testee1.setFirstNameDative("name1");

        Student testee2 = new Student();
        testee2.setFirstNameDative("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInMiddleNameDative() {
        Student testee1 = new Student();
        testee1.setMiddleNameDative("name1");

        Student testee2 = new Student();
        testee2.setMiddleNameDative("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInStudentIdNumber() {
        Student testee1 = new Student();
        testee1.setStudentIdNumber("number1");

        Student testee2 = new Student();
        testee2.setStudentIdNumber("number2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInGroup() {
        Group group1 = new Group();
        group1.setId(generateId());

        Group group2 = new Group();
        group2.setId(generateId());

        Student testee1 = new Student();
        testee1.setGroup(group1);

        Student testee2 = new Student();
        testee2.setGroup(group2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInSpeciality() {
        Speciality speciality1 = new Speciality();
        speciality1.setId(generateId());

        Speciality speciality2 = new Speciality();
        speciality2.setId(generateId());

        Student testee1 = new Student();
        testee1.setSpeciality(speciality1);

        Student testee2 = new Student();
        testee2.setSpeciality(speciality2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInDivision() {
        Student testee1 = new Student();
        testee1.setDivision(Student.Division.EVENINGSTUDY);

        Student testee2 = new Student();
        testee2.setDivision(Student.Division.EXTRAMURAL);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInStudyForm() {
        Student testee1 = new Student();
        testee1.setStudyForm(Student.StudyForm.BUDGET);

        Student testee2 = new Student();
        testee2.setStudyForm(Student.StudyForm.COMMERCIAL);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInRole() {
        Student testee1 = new Student();
        testee1.setRole(Student.Role.CAPTAIN);

        Student testee2 = new Student();
        testee2.setRole(null);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInEnrollmentOrder() {
        EnrollmentOrder enrollmentOrder1 = new EnrollmentOrder();
        enrollmentOrder1.setId(generateId());

        EnrollmentOrder enrollmentOrder2 = new EnrollmentOrder();
        enrollmentOrder2.setId(generateId());

        Student testee1 = new Student();
        testee1.setEnrollmentOrder(enrollmentOrder1);

        Student testee2 = new Student();
        testee2.setEnrollmentOrder(enrollmentOrder2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInReleaseDate() {
        Date releaseDate1 = new Date(1);
        Date releaseDate2 = new Date(2);

        Student testee1 = new Student();
        testee1.setReleaseDate(releaseDate1);

        Student testee2 = new Student();
        testee2.setReleaseDate(releaseDate2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInAdditionalStudentData() {
        Student.AdditionalStudentData additionalDate1 = new Student.AdditionalStudentData();
        additionalDate1.setId(generateId());

        Student.AdditionalStudentData additionalDate2 = new Student.AdditionalStudentData();
        additionalDate2.setId(generateId());

        Student testee1 = new Student();
        testee1.setAdditionalData(additionalDate1);

        Student testee2 = new Student();
        testee2.setAdditionalData(additionalDate2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInAdditionalStipends() {
        Stipend stipend1 = new Stipend();
        stipend1.setId(generateId());

        Stipend stipend2 = new Stipend();
        stipend2.setId(generateId());

        Student testee1 = new Student();
        testee1.setStipends(ImmutableSet.of(stipend1));

        Student testee2 = new Student();
        testee2.setStipends(ImmutableSet.of(stipend2));

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsAndHashCodeWhenContentEquals() {
        Student testee1 = makeStudent();
        Student testee2 = makeStudent();

        testWhenObjectsAreEqual(testee1, testee2);
    }
}
