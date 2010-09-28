package ru.sgu.csit.inoc.deansoffice;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.reports.ReportPdfProcessor;

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

    public void testPdfGenerate() {
        Student student = new Student();
        Reference ref = new Reference();
        EnrollmentOrder enrolOrder = new EnrollmentOrder();

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

        String templName = AppTest.class.getResource("/template.xml").getFile();

        templName = templName.replace("%20", " ");
        System.out.println(templName);
        ref.setPrintTemplate(new Template(templName));
        ref.build(student);
        ReportPdfProcessor.getInstance().generate(ref);
    }
}
