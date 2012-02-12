package ru.sgu.csit.inoc.deansoffice.domain;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static ru.sgu.csit.inoc.deansoffice.CommonTestUtils.generateId;
import static ru.sgu.csit.inoc.deansoffice.CommonTestUtils.testWhenObjectsAreDifferent;
import static ru.sgu.csit.inoc.deansoffice.CommonTestUtils.testWhenObjectsAreEqual;
import static ru.sgu.csit.inoc.deansoffice.DomainTestUtils.makeGroup;

public class GroupTestCase {

    @Test
    public void testEqualsWhenOtherHasDifferentClass() {

        // INIT
        Group group = new Group();

        // ACT
        boolean result = group.equals(new Object());

        assertFalse("result", result);
    }

    @Test
    public void testEqualsWhenContentDiffersInId() {
        Group testee1 = new Group();
        testee1.setId(generateId());

        Group testee2 = new Group();
        testee2.setId(generateId());

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInName() {
        Group testee1 = new Group();
        testee1.setName("name1");

        Group testee2 = new Group();
        testee2.setName("name2");

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInCourse() {
        Group testee1 = new Group();
        testee1.setCourse(1);

        Group testee2 = new Group();
        testee2.setCourse(2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsWhenContentDiffersInSpeciality() {
        Speciality speciality1 = new Speciality();
        speciality1.setId(generateId());

        Speciality speciality2 = new Speciality();
        speciality2.setId(generateId());

        Group testee1 = new Group();
        testee1.setSpeciality(speciality1);

        Group testee2 = new Group();
        testee2.setSpeciality(speciality2);

        testWhenObjectsAreDifferent(testee1, testee2);
    }

    @Test
    public void testEqualsAndHashCodeWhenContentEquals() {
        Group testee1 = makeGroup();
        Group testee2 = makeGroup();

        testWhenObjectsAreEqual(testee1, testee2);
    }
}
