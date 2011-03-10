package ru.sgu.csit.inoc.deansoffice.dao.impl;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 09.03.11
 * Time: 10:05
 */

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

import java.util.*;

/**
 * Unit test for SudentDAOImpl.
 */
public class SudentDAOImplTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SudentDAOImplTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ru.sgu.csit.inoc.deansoffice.dao.impl.SudentDAOImplTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private static StudentDAO studentDAO = applicationContext.getBean(StudentDAOImpl.class);
    private static List<Student> students;
    static {
         students = studentDAO.findAll();
    }

    public void testFindByGroup() {
        System.out.println("-- testFindByGroup -----");
        Map<Group, Integer> groupsMap = new HashMap<Group, Integer>();
        for (Student student : students) {
            Group group = student.getGroup();
            Integer value = 1;

            if (groupsMap.containsKey(group)) {
                value = groupsMap.get(group) + 1;
            }
            groupsMap.put(group, value);
        }
        for (Map.Entry<Group, Integer> entry: groupsMap.entrySet()) {
            Group group = entry.getKey();
            Integer value = entry.getValue();

            System.out.println("[findAll] " + group.getName() + " : " + value);
            Integer newValue = studentDAO.findByGroup(group).size();
            System.out.println("[findByGroup] " + group.getName() + " : " + newValue);
            assertTrue("Количество студентов в " + group.getName() + " группе.", value.equals(newValue));
        }
        System.out.println("------------------------\n");
    }

    public void testFindByGroupId() {
        System.out.println("-- testFindByGroupId ---");
        Map<Group, Integer> groupsMap = new HashMap<Group, Integer>();
        for (Student student : students) {
            Group group = student.getGroup();
            Integer value = 1;

            if (groupsMap.containsKey(group)) {
                value = groupsMap.get(group) + 1;
            }
            groupsMap.put(group, value);
        }
        for (Map.Entry<Group, Integer> entry: groupsMap.entrySet()) {
            Group group = entry.getKey();
            Integer value = entry.getValue();

            System.out.println("[findAll] " + group.getName() + " : " + value);
            Integer newValue = studentDAO.findByGroupId(group.getId()).size();
            System.out.println("[findByGroupId] " + group.getName() + " : " + newValue);
            assertTrue("Количество студентов в " + group.getName() + " группе.", value.equals(newValue));
        }
        System.out.println("------------------------\n");
    }

    public void testFindBySpecialityIdAndCourse() {
        System.out.println("-- testFindBySpecialityIdAndCourse --");
        Set<Speciality> specialities = new HashSet<Speciality>();
        Set<Integer> courses = new HashSet<Integer>();
        for (Student student : students) {
            specialities.add(student.getSpeciality());
            courses.add(student.getCourse());
        }
        for (Integer course : courses) {
            for (Speciality speciality : specialities) {
                Integer count = 0;
                for (Student student : students) {
                    if (student.getSpeciality().getId().equals(speciality.getId())
                            && student.getCourse().equals(course)) {
                        count++;
                    }
                }
                System.out.println("[findAll] " + speciality.getId() + ", " + course + " : "
                        + count);
                Integer newCount = studentDAO.findBySpecialityIdAndCourse(speciality.getId(), course).size();
                System.out.println("[findBySpecialityIdAndCourse] " + speciality.getId() + ", " + course + " : "
                        + newCount);
                assertTrue("Количество студентов " + course + " курса, "  + speciality.getId() + " специальности.",
                        count.equals(newCount));
            }
        }
        System.out.println("------------------------\n");
    }
}
