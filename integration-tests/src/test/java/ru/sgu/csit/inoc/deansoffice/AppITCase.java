package ru.sgu.csit.inoc.deansoffice;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.PhotoDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Reference;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.domain.StudentDossier;
import ru.sgu.csit.inoc.deansoffice.services.PhotoService;
import ru.sgu.csit.inoc.deansoffice.services.ReferenceService;
import ru.sgu.csit.inoc.deansoffice.services.StudentDossierService;
import ru.sgu.csit.inoc.deansoffice.services.impl.StudentDossierServiceImpl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AppITCase {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppITCase.class);

    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private GroupDAO groupDAO;
    @Autowired
    private PhotoDAO photoDAO;

    @Autowired
    private PhotoService photoService;
    @Autowired
    private ReferenceService referenceService;

    public void /*skip_*/ testPdfGenerate() {
        /* // Отладка загрузки фотографий
        Photo photo;
        try {
            photo = photoService.loadFromFile("C:/temp/images/photo.jpg");
        } catch (IOException e) {
            throw new RuntimeException("Photo not found!!!", e);
        }

        LOGGER.info("Photo size: " + photo.getData().length);
        Long idPhoto = photoDAO.save(photo);

        Photo newPhoto = photoDAO.findById(idPhoto);
        try {
            photoService.loadData(newPhoto);
        } catch (IOException e) {
            throw new RuntimeException("Photo data not found!!!", e);
        }
        LOGGER.info("New Photo size: " + newPhoto.getData().length);
        */
        Group group = groupDAO.findAll().get(0);
        List<Student> students = studentDAO.findByGroup(group);
        Student student = students.get(students.size() / 2);//new Student();
//        try {
//            photoService.loadData(student.getAdditionalData().getPhoto());
//        } catch (IOException e) {
//            throw new RuntimeException("Photo data not found!!!", e);
//        }
        //Reference ref = new Reference();
        /*EnrollmentOrder enrolOrder = new EnrollmentOrder();

        enrolOrder.setNumber("22-0107");
        enrolOrder.setSignedDate(new GregorianCalendar().getTime());
        enrolOrder.setEnrollmentDate(new GregorianCalendar(2010, Calendar.SEPTEMBER, 1).getTime());
        enrolOrder.setReleaseDate(new GregorianCalendar(2015, Calendar.JULY, 1).getTime());

        Faculty csit = new Faculty();

        csit.setFullName("факультет компьютерных наук и информационных технологий");
        csit.setShortName("КНиИТ");
        csit.setDean(new Employee());
        csit.getDean().setFirstName("Антонина");
        csit.getDean().setMiddleName("Гавриловна");
        csit.getDean().setLastName("Фёдорова");
        csit.setRector(new Employee());
        csit.getRector().setFirstName("Леонид");
        csit.getRector().setMiddleName("Юрьевич");
        csit.getRector().setLastName("Коссович");
        csit.getRector().setDegree("д.ф.-м.н., профессор");

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
        StudentDossierService dossierService = new StudentDossierServiceImpl();
        dossierService.generatePrintForm(dossier, student, outputStream);

        //==============================================

        List<Reference> references = new ArrayList<Reference>();
        try {
            outputStream = new FileOutputStream(new File("test_multi.pdf"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        for (Student theStudent : students) {
            if (theStudent.getEnrollmentOrder() != null) {     //
                Reference reference = new Reference();
                reference.setType(Reference.ReferenceType.REFERENCE_1);
                reference.setOwnerId(theStudent.getId());
                references.add(reference);
            }                                                  //
        }
        referenceService.generatePrintForm(references, outputStream);
    }

    @Test
    public void stab() {
        assertTrue(true);
    }
}
