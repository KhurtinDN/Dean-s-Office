package ru.sgu.csit.inoc.deansoffice.dao.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.FacultyDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 21.03.11
 * Time: 14:59
 */

/**
 * Unit test for FacultyDAOImpl.
 */
public class FacultyDAOImplTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FacultyDAOImplTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ru.sgu.csit.inoc.deansoffice.dao.impl.FacultyDAOImplTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private static FacultyDAO facultyDAO = applicationContext.getBean(FacultyDAOImpl.class);
    private static List<Faculty> faculties;
    static {
         faculties = facultyDAO.findAll();
    }

    public void testFindByShortName() {
        System.out.println("-- testFindByShortName -");
        System.out.println("[findAll] : total faculties - " + faculties.size());
        for (int i = 0; i < faculties.size(); ++i) {
            Faculty faculty = faculties.get(i);

            System.out.println((i + 1) + ". " + faculty.getShortName() + ", Id = " + faculty.getId());
            List<Faculty> newFaculties = facultyDAO.findByShortName(faculty.getShortName());
            System.out.println("   found " + newFaculties.size() + " faculties with name " + faculty.getShortName() +
                ", first Id = " + newFaculties.get(0).getId());
            assertTrue("Поиск факультета по короткому имени " + faculty.getShortName() + ".", faculty.getId()
                    .equals(newFaculties.get(0).getId()));
        }
        System.out.println("------------------------\n");
    }
}
