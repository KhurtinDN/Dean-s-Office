package ru.sgu.csit.inoc.deansoffice;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.reports.ReportPdfProcessor;

import java.util.Date;

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
        Order enrolOrder = new Order();

        enrolOrder.setNumber("22-0107");
        enrolOrder.setSignedDate(new Date());
        enrolOrder.addData("EnrollmentDate", new Date(110, 8, 1));
        enrolOrder.addData("ReleaseDate", new Date(115, 6, 1));

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

        ref.setPrintTemplate(new Template("template.xml"));
        ref.build(student);
        ReportPdfProcessor.getInstance().generate(ref, student);
    }
}
