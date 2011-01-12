package ru.sgu.csit.inoc.deansoffice;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.hibernate.Hibernate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.orm.hibernate3.HibernateTemplate;
import ru.sgu.csit.inoc.deansoffice.dao.PhotoDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.dao.impl.PhotoDAOImpl;
import ru.sgu.csit.inoc.deansoffice.dao.impl.StudentDAOImpl;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.reports.ReportPdfProcessor;
import ru.sgu.csit.inoc.deansoffice.services.PhotoService;
import ru.sgu.csit.inoc.deansoffice.services.impl.PhotoServiceImpl;

import java.io.*;

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

    private static PhotoDAO photoDAO = applicationContext.getBean(PhotoDAOImpl.class);

    public void /*skip_*/ testPdfGenerate() {
        PhotoService photoService = new PhotoServiceImpl();

        /* // Отладка загрузки фотографий
        Photo photo;
        try {
            photo = photoService.loadFromFile("C:/temp/images/photo.jpg");
        } catch (IOException e) {
            throw new RuntimeException("Photo not found!!!", e);
        }

        System.out.println("Photo size: " + photo.getData().length);
        Long idPhoto = photoDAO.save(photo);

        Photo newPhoto = photoDAO.findById(idPhoto);
        try {
            photoService.loadData(newPhoto);
        } catch (IOException e) {
            throw new RuntimeException("Photo data not found!!!", e);
        }
        System.out.println("New Photo size: " + newPhoto.getData().length);
        */

        Student student = studentDAO.findById(55L);//findAll().get(0);//new Student();
        try {
            photoService.loadData(student.getAdditionalData().getPhoto());
        } catch (IOException e) {
            throw new RuntimeException("Photo data not found!!!", e);
        }
        //Reference ref = new Reference();
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
        //studentDAO.initialize(student.getAdditionalData());
        StudentDossier dossier = new StudentDossier();
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File("test.pdf"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String templName = AppTest.class.getResource("/dossier.xml").getFile();

        templName = templName.replace("%20", " ");
        System.out.println(templName);
        dossier.setPrintTemplate(new Template(templName));
        dossier.build(student);
        ReportPdfProcessor.getInstance().generate(dossier, outputStream);
    }
}
