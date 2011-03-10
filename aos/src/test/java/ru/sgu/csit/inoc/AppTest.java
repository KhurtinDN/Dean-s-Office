package ru.sgu.csit.inoc;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.AdditionalStudentDataDAO;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.PassportDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.dao.impl.GroupDAOImpl;
import ru.sgu.csit.inoc.deansoffice.dao.impl.StudentDAOImpl;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Passport;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        StudentDAO studentDAO = applicationContext.getBean(StudentDAO.class);
        GroupDAO groupDAO = applicationContext.getBean(GroupDAO.class);
        PassportDAO passportDAO = applicationContext.getBean(PassportDAO.class);
        AdditionalStudentDataDAO dataDAO = applicationContext.getBean(AdditionalStudentDataDAO.class);

        Group group = groupDAO.findByName("111").get(0);
        List<Student> students = studentDAO.findByGroup(group);
        System.out.println(group.getName() + ": " + students.size());
        Student student = students.get(0);
        System.out.println(student.getAdditionalData());
        System.out.println(student.getAdditionalData().getPassports().size());

        Passport passport = new Passport(student);
//        passportDAO.saveOrUpdate(passport);
        student.getAdditionalData().addPassport(passport);
//        dataDAO.saveOrUpdate(student.getAdditionalData());
        studentDAO.saveOrUpdate(student);

        students = studentDAO.findByGroup(group);
//        for (Student stud : students) {
//            System.out.println(stud.getGroup().getName() + ": " + stud.getId() + ", "
//                    + stud.getAdditionalData().getPassports().size());
//        }
        System.out.println(group.getName() + " - " + students.size());
        for (Student stud : students) {
            System.out.println(stud.getGroup().getName() + ": " + stud.getId() + ", "
                    + stud.getAdditionalData().getPassports().size());
        }
//        student = studentDAO.findByGroupId(group.getId()).get(0);
//        System.out.println(student.getAdditionalData().getPassports().size());

        assertTrue(true);
    }
}
