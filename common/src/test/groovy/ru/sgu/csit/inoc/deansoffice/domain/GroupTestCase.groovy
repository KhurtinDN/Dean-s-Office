package ru.sgu.csit.inoc.deansoffice.domain

import org.junit.Test
import static org.junit.Assert.assertFalse
import static ru.sgu.csit.inoc.deansoffice.CommonTestUtils.*
import static ru.sgu.csit.inoc.deansoffice.DomainTestUtils.makeGroup

/**
 * @author Denis Khurtin
 */
class GroupTestCase {

    @Test
    public void testEqualsWhenOtherHasDifferentClass() {

        // INIT
        final group = new Group()

        // ACT
        final result = group.equals(new Object())

        assertFalse "result", result
    }

    @Test
    public void testEqualsWhenContentDiffersInId() {
        final testee1 = new Group(id: generateId())
        final testee2 = new Group(id: generateId())

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInName() {
        final testee1 = new Group(name: "name1")
        final testee2 = new Group(name: "name2")

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInCourse() {
        final testee1 = new Group(course: 1)
        final testee2 = new Group(course: 2)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsWhenContentDiffersInSpeciality() {
        final speciality1 = new Speciality(id: generateId())
        final speciality2 = new Speciality(id: generateId())

        final testee1 = new Group(speciality: speciality1)
        final testee2 = new Group(speciality: speciality2)

        testWhenObjectsAreDifferent(testee1, testee2)
    }

    @Test
    public void testEqualsAndHashCodeWhenContentEquals() {
        final testee1 = makeGroup();
        final testee2 = makeGroup();

        testWhenObjectsAreEqual(testee1, testee2);
    }
}
