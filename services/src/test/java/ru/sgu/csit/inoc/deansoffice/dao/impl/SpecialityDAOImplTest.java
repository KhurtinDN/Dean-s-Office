package ru.sgu.csit.inoc.deansoffice.dao.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 22.03.11
 * Time: 11:18
 */

/**
 * Unit test for SpecialityDAOImpl.
 */
public class SpecialityDAOImplTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public SpecialityDAOImplTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ru.sgu.csit.inoc.deansoffice.dao.impl.SpecialityDAOImplTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private static SpecialityDAO specialityDAO = applicationContext.getBean(SpecialityDAOImpl.class);
    private static List<Speciality> specialities;

    static {
        specialities = specialityDAO.findAll();
    }

    public void testFindByShortName() {
        System.out.println("-- testFindByShortName -");
        Set<String> shortNames = new HashSet<String>();
        for (Speciality speciality : specialities) {
            shortNames.add(speciality.getShortName());
        }
        System.out.println("[findAll] : total specialities - " + specialities.size());
        System.out.println("            total shortNames - " + shortNames.size());

        int totalSpecialities = 0;
        System.out.println("[findByShortName] : ");
        for (String name : shortNames) {
            List<Speciality> newSpecialities = specialityDAO.findByShortName(name);
            System.out.println("   found " + newSpecialities.size() + " specialities with shortName " + name);
            totalSpecialities += newSpecialities.size();
            boolean result = true;
            for (Speciality speciality : newSpecialities) {
                if (!name.equals(speciality.getShortName())) {
                    result = false;
                }
            }
            assertTrue("Поиск специальностей по короткому имени " + name + ".", result);
        }
        System.out.println("   total found specialities " + totalSpecialities);
        assertTrue("Общее количество найденных специальностей.", specialities.size() == totalSpecialities);
        System.out.println("------------------------\n");
    }

    public void testFindByFaculty() {
        System.out.println("-- testFindByFaculty ---");
        Set<Faculty> faculties = new HashSet<Faculty>();
        for (Speciality speciality : specialities) {
            faculties.add(speciality.getFaculty());
        }
        System.out.println("[findAll] : total specialities - " + specialities.size());
        System.out.println("            total faculties - " + faculties.size());

        int totalSpecialities = 0;
        System.out.println("[findByShortFaculty] : ");
        for (Faculty faculty : faculties) {
            List<Speciality> newSpecialities = specialityDAO.findByFaculty(faculty);
            System.out.println("   found " + newSpecialities.size() + " specialities with faculty "
                    + faculty.getShortName());
            totalSpecialities += newSpecialities.size();
            boolean result = true;
            for (Speciality speciality : newSpecialities) {
                if (!faculty.getId().equals(speciality.getFaculty().getId())) {
                    result = false;
                }
            }
            assertTrue("Поиск специальностей по факультету " + faculty.getShortName() + ".", result);
        }
        System.out.println("   total found specialities " + totalSpecialities);
        assertTrue("Общее количество найденных специальностей.", specialities.size() == totalSpecialities);
        System.out.println("------------------------\n");
    }

    public void testFindByFacultyId() {
        System.out.println("-- testFindByFacultyId ---");
        Set<Long> facultiesId = new HashSet<Long>();
        for (Speciality speciality : specialities) {
            facultiesId.add(speciality.getFaculty().getId());
        }
        System.out.println("[findAll] : total specialities - " + specialities.size());
        System.out.println("            total faculties - " + facultiesId.size());

        int totalSpecialities = 0;
        System.out.println("[findByShortFacultyId] : ");
        for (Long facultyId : facultiesId) {
            List<Speciality> newSpecialities = specialityDAO.findByFacultyId(facultyId);
            System.out.println("   found " + newSpecialities.size() + " specialities with faculty Id "
                    + facultyId);
            totalSpecialities += newSpecialities.size();
            boolean result = true;
            for (Speciality speciality : newSpecialities) {
                if (!facultyId.equals(speciality.getFaculty().getId())) {
                    result = false;
                }
            }
            assertTrue("Поиск специальностей по Id факультета " + facultyId + ".", result);
        }
        System.out.println("   total found specialities " + totalSpecialities);
        assertTrue("Общее количество найденных специальностей.", specialities.size() == totalSpecialities);
        System.out.println("------------------------\n");
    }
}


