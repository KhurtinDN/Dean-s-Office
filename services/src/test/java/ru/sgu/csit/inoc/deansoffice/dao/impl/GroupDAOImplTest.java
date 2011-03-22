package ru.sgu.csit.inoc.deansoffice.dao.impl;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 22.03.11
 * Time: 10:41
 */

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Unit test for GroupDAOImpl.
 */
public class GroupDAOImplTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public GroupDAOImplTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ru.sgu.csit.inoc.deansoffice.dao.impl.GroupDAOImplTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private static GroupDAO groupDAO = applicationContext.getBean(GroupDAOImpl.class);
    private static List<Group> groups;

    static {
        groups = groupDAO.findAll();
    }

    public void testFindByCourseAndSpeciality() {
        System.out.println("-- testFindByCourseAndSpeciality");
        Set<Integer> courses = new HashSet<Integer>();
        Set<Speciality> specialities = new HashSet<Speciality>();
        for (Group group : groups) {
            courses.add(group.getCourse());
            specialities.add(group.getSpeciality());
        }
        System.out.println("[findAll] : total groups - " + groups.size());
        System.out.println("            total courses - " + courses.size());
        System.out.println("            total specialities - " + specialities.size());

        int totalGroups = 0;
        System.out.println("[findByCourseAndSpeciality] : ");
        for (Integer course : courses) {
            for (Speciality speciality : specialities) {
                List<Group> newGroups = groupDAO.findByCourseAndSpeciality(course, speciality);
                System.out.println("   found " + newGroups.size() + " groups with course " + course +
                        " and speciality " + speciality.getShortName());
                totalGroups += newGroups.size();
                boolean result = true;
                for (Group group : newGroups) {
                    if (!course.equals(group.getCourse()) || !speciality.getId().equals(group.getSpeciality().getId())) {
                        result = false;
                    }
                }
                assertTrue("Поиск групп по курсу " + course + " и специальности " + speciality.getShortName() + ".",
                        result);
            }
        }
        System.out.println("   total found groups " + totalGroups);
        assertTrue("Общее количество найденных групп.", groups.size() == totalGroups);
        System.out.println("------------------------\n");
    }

    public void testFindByCourseAndSpecialityId() {
        System.out.println("-- testFindByCourseAndSpecialityId");
        Set<Integer> courses = new HashSet<Integer>();
        Set<Long> specialitiesId = new HashSet<Long>();
        for (Group group : groups) {
            courses.add(group.getCourse());
            specialitiesId.add(group.getSpeciality().getId());
        }
        System.out.println("[findAll] : total groups - " + groups.size());
        System.out.println("            total courses - " + courses.size());
        System.out.println("            total specialitiesId - " + specialitiesId.size());

        int totalGroups = 0;
        System.out.println("[findByCourseAndSpecialityId] : ");
        for (Integer course : courses) {
            for (Long specialityId : specialitiesId) {
                List<Group> newGroups = groupDAO.findByCourseAndSpecialityId(course, specialityId);
                System.out.println("   found " + newGroups.size() + " groups with course " + course +
                        " and specialityId " + specialityId);
                totalGroups += newGroups.size();
                boolean result = true;
                for (Group group : newGroups) {
                    if (!course.equals(group.getCourse()) || !group.getSpeciality().getId().equals(specialityId)) {
                        result = false;
                    }
                }
                assertTrue("Поиск групп по курсу " + course + " и Id специальности " + specialityId + ".", result);
            }
        }
        System.out.println("   total found groups " + totalGroups);
        assertTrue("Общее количество найденных групп.", groups.size() == totalGroups);
        System.out.println("------------------------\n");
    }

    public void testFindByName() {
        System.out.println("-- testFindByName ------");
        Set<String> names = new HashSet<String>();
        for (Group group : groups) {
            names.add(group.getName());
        }
        System.out.println("[findAll] : total groups - " + groups.size());
        System.out.println("            total names - " + names.size());

        int totalGroups = 0;
        System.out.println("[findByName] : ");
        for (String name : names) {
            List<Group> newGroups = groupDAO.findByName(name);
            System.out.println("   found " + newGroups.size() + " groups with name " + name);
            totalGroups += newGroups.size();
            boolean result = true;
            for (Group group : newGroups) {
                if (!name.equals(group.getName())) {
                    result = false;
                }
            }
            assertTrue("Поиск групп по имени " + name + ".", result);
        }
        System.out.println("   total found groups " + totalGroups);
        assertTrue("Общее количество найденных групп.", groups.size() == totalGroups);
        System.out.println("------------------------\n");
    }
}

