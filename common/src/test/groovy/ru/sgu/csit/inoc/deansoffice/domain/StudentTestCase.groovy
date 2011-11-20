package ru.sgu.csit.inoc.deansoffice.domain

import com.google.common.collect.ImmutableSet
import org.junit.Test
import static org.junit.Assert.assertFalse
import static ru.sgu.csit.inoc.deansoffice.CommonTestUtils.*
import static ru.sgu.csit.inoc.deansoffice.DomainTestUtils.makeStudent

/**
 * @author Denis Khurtin
 */
class StudentTestCase {

    @Test
    public void testEqualsWhenOtherHasDifferentClass() {

        // INIT
        final testee = new Student()
        final someObject = new Object()

        // ACT
        final result = testee.equals(someObject)

        // CHECK
        assertFalse "result", result
    }

    @Test
    public void testEqualsWhenContentDiffersInId() {
        final testee1 = new Student(id: generateId())
        final testee2 = new Student(id: generateId())

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInBirthday() {
        final birthday1 = new Date(generateId())
        final birthday2 = new Date(generateId())

        final testee1 = new Student(birthday: birthday1)
        final testee2 = new Student(birthday: birthday2)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInSex() {
        final testee1 = new Student(sex: Person.Sex.MALE)
        final testee2 = new Student(sex: Person.Sex.FEMALE)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInFirstName() {
        final testee1 = new Student(firstName: "name1")
        final testee2 = new Student(firstName: "name2")

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInMiddleName() {
        final testee1 = new Student(middleName: "name1")
        final testee2 = new Student(middleName: "name2")

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInLastName() {
        final testee1 = new Student(lastName: "name1")
        final testee2 = new Student(lastName: "name2")

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInFirstNameDative() {
        final testee1 = new Student(firstNameDative: "name1")
        final testee2 = new Student(firstNameDative: "name2")

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInMiddleNameDative() {
        final testee1 = new Student(middleNameDative: "name1")
        final testee2 = new Student(middleNameDative: "name2")

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInLastNameDative() {
        final testee1 = new Student(lastNameDative: "name1")
        final testee2 = new Student(lastNameDative: "name2")

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInStudentIdNumber() {
        final testee1 = new Student(studentIdNumber: "number1")
        final testee2 = new Student(studentIdNumber: "number2")

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInGroup() {
        final group1 = new Group(id: generateId())
        final group2 = new Group(id: generateId())

        final testee1 = new Student(group: group1)
        final testee2 = new Student(group: group2)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInSpeciality() {
        final speciality1 = new Speciality(id: generateId())
        final speciality2 = new Speciality(id: generateId())

        final testee1 = new Student(speciality: speciality1)
        final testee2 = new Student(speciality: speciality2)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInDivision() {
        final testee1 = new Student(division: Student.Division.EVENINGSTUDY)
        final testee2 = new Student(division: Student.Division.EXTRAMURAL)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInStudyForm() {
        final testee1 = new Student(studyForm: Student.StudyForm.BUDGET)
        final testee2 = new Student(studyForm: Student.StudyForm.COMMERCIAL)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInRole() {
        final testee1 = new Student(role: Student.Role.CAPTAIN)
        final testee2 = new Student(role: null)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInEnrollmentOrder() {
        final enrollmentOrder1 = new EnrollmentOrder(id: generateId())
        final enrollmentOrder2 = new EnrollmentOrder(id: generateId())

        final testee1 = new Student(enrollmentOrder: enrollmentOrder1)
        final testee2 = new Student(enrollmentOrder: enrollmentOrder2)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInReleaseDate() {
        final releaseDate1 = new Date(generateId())
        final releaseDate2 = new Date(generateId())

        final testee1 = new Student(releaseDate: releaseDate1)
        final testee2 = new Student(releaseDate: releaseDate2)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInAdditionalStudentData() {
        final additionalDate1 = new Student.AdditionalStudentData(id: generateId())
        final additionalDate2 = new Student.AdditionalStudentData(id: generateId())

        final testee1 = new Student(additionalData: additionalDate1)
        final testee2 = new Student(additionalData: additionalDate2)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInAdditionalStipends() {
        final stipend1 = new Stipend(id: generateId())
        final stipend2 = new Stipend(id: generateId())

        final testee1 = new Student(stipends: ImmutableSet.of(stipend1))
        final testee2 = new Student(stipends: ImmutableSet.of(stipend2))

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsAndHashCodeWhenContentEquals() {
        final testee1 = makeStudent();
        final testee2 = makeStudent();

        testWhenObjectsAreEqual(testee1, testee2);
    }
}
