package ru.sgu.csit.inoc.deansoffice;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.dao.impl.StudentDAOImpl;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.reports.ReportPdfProcessor;

import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ru.sgu.csit.inoc.deansoffice.AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private static StudentDAO studentDAO = applicationContext.getBean(StudentDAOImpl.class);

    public void testPdfGenerate() {
        Student student = studentDAO.findAll().get(0);//new Student();
        Reference ref = new Reference();
        /*EnrollmentOrder enrolOrder = new EnrollmentOrder();

        enrolOrder.setNumber("22-0107");
        enrolOrder.setSignedDate(new GregorianCalendar().getTime());
        enrolOrder.setEnrollmentDate(new GregorianCalendar(2010, Calendar.SEPTEMBER, 1).getTime());
        enrolOrder.setReleaseDate(new GregorianCalendar(2015, Calendar.JULY, 1).getTime());

        Faculty csit = new Faculty();

        csit.setFullName("факультет компьютерных наук и информационных технологий");
        csit.setShortName("КНиИТ");
        csit.setDean(new Dean());
        csit.getDean().setFirstName("Антонина");
        csit.getDean().setMiddleName("Гавриловна");
        csit.getDean().setLastName("Фёдорова");
        csit.setRector(new Rector());
        csit.getRector().setFirstName("Леонид");
        csit.getRector().setMiddleName("Юрьевич");
        csit.getRector().setLastName("Коссович");
        csit.getRector().setDegree("д.ф.-м.н., профессор");

        student.setCource(1);
        student.setDivision(Student.Division.INTRAMURAL);
        student.setEnrollmentOrder(enrolOrder);
        student.setFirstName("Александр");
        student.setFirstNameDative("Александру");
        student.setLastName("Заслоновский");
        student.setLastNameDative("Заслоновскому");
        student.setMiddleName("Александрович");
        student.setMiddleNameDative("Александровичу");
        student.setSpeciality(new Speciality());
        student.getSpeciality().setFaculty(csit);
        student.setStudyForm(Student.StudyForm.BUDGET);
        */
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File("test.pdf"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String templName = AppTest.class.getResource("/reference-2.xml").getFile();

        templName = templName.replace("%20", " ");
        System.out.println(templName);
        ref.setPrintTemplate(new Template(templName));
        ref.build(student);
        ReportPdfProcessor.getInstance().generate(ref, outputStream);
    }
}
