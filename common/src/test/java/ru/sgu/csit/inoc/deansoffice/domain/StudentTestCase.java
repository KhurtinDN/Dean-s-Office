package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static ru.sgu.csit.inoc.deansoffice.CommonTestUtils.testWhenObjectsAreDifferent;
import static ru.sgu.csit.inoc.deansoffice.CommonTestUtils.testWhenObjectsAreEqual;

/**
 * User: hd
 * Date: 5/29/11
 * Time: 5:38 PM
 */
public class StudentTestCase {

    @Test
    public void testEqualsWhenOtherHasDifferentClass() {
        final Student testee = new Student();

        final boolean result = testee.equals(new Object());

        assertThat("result", result, is(false));
    }

    @Test
    public void testEqualsWhenContentDiffersInId() {
        final Student testee1 = new Student();
        testee1.setId(0L);

        final Student testee2 = new Student();
        testee2.setId(1L);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInBirthday() {
        final Student testee1 = new Student();
        testee1.setBirthday(new Date(1L));

        final Student testee2 = new Student();
        testee2.setBirthday(new Date(2L));

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInSex() {
        final Student testee1 = new Student();
        testee1.setSex(Person.Sex.MALE);

        final Student testee2 = new Student();
        testee2.setSex(Person.Sex.FEMALE);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInFirstName() {
        final Student testee1 = new Student();
        testee1.setFirstName("name1");

        final Student testee2 = new Student();
        testee2.setFirstName("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInMiddleName() {
        final Student testee1 = new Student();
        testee1.setMiddleName("name1");

        final Student testee2 = new Student();
        testee2.setMiddleName("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInLastName() {
        final Student testee1 = new Student();
        testee1.setLastName("name1");

        final Student testee2 = new Student();
        testee2.setLastName("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInFirstNameDative() {
        final Student testee1 = new Student();
        testee1.setFirstNameDative("name1");

        final Student testee2 = new Student();
        testee2.setFirstNameDative("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInMiddleNameDative() {
        final Student testee1 = new Student();
        testee1.setMiddleNameDative("name1");

        final Student testee2 = new Student();
        testee2.setMiddleNameDative("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInLastNameDative() {
        final Student testee1 = new Student();
        testee1.setLastNameDative("name1");

        final Student testee2 = new Student();
        testee2.setLastNameDative("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInStudentIdNumber() {
        final Student testee1 = new Student();
        testee1.setStudentIdNumber("number1");

        final Student testee2 = new Student();
        testee2.setStudentIdNumber("number2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInCourse() {
        final Student testee1 = new Student();
        testee1.setCourse(1);

        final Student testee2 = new Student();
        testee2.setCourse(2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInGroup() {
        final Group group1 = new Group();
        group1.setId(1L);

        final Group group2 = new Group();
        group1.setId(2L);

        final Student testee1 = new Student();
        testee1.setGroup(group1);

        final Student testee2 = new Student();
        testee2.setGroup(group2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInSpeciality() {
        final Speciality speciality1 = new Speciality();
        speciality1.setId(1L);

        final Speciality speciality2 = new Speciality();
        speciality2.setId(2L);

        final Student testee1 = new Student();
        testee1.setSpeciality(speciality1);

        final Student testee2 = new Student();
        testee2.setSpeciality(speciality2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInDivision() {
        final Student testee1 = new Student();
        testee1.setDivision(Student.Division.EVENINGSTUDY);

        final Student testee2 = new Student();
        testee2.setDivision(Student.Division.EXTRAMURAL);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInStudyForm() {
        final Student testee1 = new Student();
        testee1.setStudyForm(Student.StudyForm.BUDGET);

        final Student testee2 = new Student();
        testee2.setStudyForm(Student.StudyForm.COMMERCIAL);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInRole() {
        final Student testee1 = new Student();
        testee1.setRole(Student.Role.CAPTAIN);

        final Student testee2 = new Student();
        testee2.setRole(null);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInEnrollmentOrder() {
        final EnrollmentOrder enrollmentOrder1 = new EnrollmentOrder();
        enrollmentOrder1.setId(1L);

        final EnrollmentOrder enrollmentOrder2 = new EnrollmentOrder();
        enrollmentOrder2.setId(2L);

        final Student testee1 = new Student();
        testee1.setEnrollmentOrder(enrollmentOrder1);

        final Student testee2 = new Student();
        testee2.setEnrollmentOrder(enrollmentOrder2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInAdditionalStudentData() {
        final Student.AdditionalStudentData additionalStudentData1 = new Student.AdditionalStudentData();
        additionalStudentData1.setId(1L);

        final Student.AdditionalStudentData additionalStudentData2 = new Student.AdditionalStudentData();
        additionalStudentData2.setId(2L);

        final Student testee1 = new Student();
        testee1.setAdditionalData(additionalStudentData1);

        final Student testee2 = new Student();
        testee2.setAdditionalData(additionalStudentData2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInAdditionalStipends() {
        final Stipend stipend1 = new Stipend();
        stipend1.setId(1L);

        final Stipend stipend2 = new Stipend();
        stipend2.setId(2L);

        final Student testee1 = new Student();
        testee1.setStipends(ImmutableSet.of(stipend1));

        final Student testee2 = new Student();
        testee2.setStipends(ImmutableSet.of(stipend2));

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsAndHashCodeWhenContentEquals() {
        final Student testee1 = makeStudent();
        final Student testee2 = makeStudent();

        testWhenObjectsAreEqual(testee1, testee2);
    }

    private static Long generateId() {
        return 0L;
    }

    private Student makeStudent() {
        final Integer course = 3;
        final Speciality testSpeciality =
                Speciality.make(
                        generateId(),
                        "test-speciality",
                        "tst-spec",
                        "test-code",
                        5,
                        Faculty.make(
                                generateId(),
                                "test-faculty",
                                "tst-fac",
                                Dean.make(
                                        generateId(),
                                        "dean-first",
                                        "dean-middle",
                                        "dean-last",
                                        "dean-deg",
                                        "dean-pos"),
                                Rector.make(generateId(),
                                        "rec-first",
                                        "rec-middle",
                                        "rec-last",
                                        "rec-deg",
                                        "rec-pos")));

        return Student.make(
                generateId(),
                new Date(111L),
                Person.Sex.MALE,
                "Иван",
                "Иванович",
                "Иванов",
                "Ивану",
                "Ивановичу",
                "Иванову",
                course,
                Group.make(
                        generateId(),
                        "test-group",
                        course,
                        testSpeciality),
                testSpeciality,
                Student.Division.EVENINGSTUDY,
                Student.StudyForm.BUDGET,
                Student.Role.CAPTAIN,
                EnrollmentOrder.make(
                        generateId(),
                        "test-number",
                        new Date(333L),
                        Template.make(
                                generateId(),
                                "test-file-name",
                                Template.TemplType.XML),
                        Order.OrderState.COMPLETED,
                        ImmutableList.<Directive>of(new Directive1()),
                        Order.OrderData.make(
                                generateId(),
                                "test-note",
                                Leader.make(generateId(), "lead-fir", "lead-mid", "lead-last", "lead-deg", "lead-pos"),
                                ImmutableList.<Coordinator>of(Coordinator.make(generateId(), "pos"))
                        ),
                        new Date(444L),
                        new Date(555L)),
                Student.AdditionalStudentData.make(
                        generateId(),
                        Photo.make(
                                generateId(),
                                "test-file-name",
                                null),
                        "test-birth-place",
                        "test-edu",
                        "test-work-info",
                        ImmutableList.<Passport>of(new Passport()),
                        "test-marital-status",
                        "test-children-info",
                        Parent.make(
                                generateId(),
                                "mother-fir",
                                "mother-mid",
                                "mother-last",
                                "test-address",
                                "test-work-info",
                                "test-phone-number"),
                        Parent.make(
                                generateId(),
                                "father-fir",
                                "father-mid",
                                "father-last",
                                "test-address",
                                "test-work-info",
                                "test-phone-number"),
                        "test-old-address",
                        "test-actual-address"),
                ImmutableSet.of(
                        Stipend.make(324L, Stipend.StipendType.SOCIAL, new Date(0), new Date(0), 666)));
    }
}
