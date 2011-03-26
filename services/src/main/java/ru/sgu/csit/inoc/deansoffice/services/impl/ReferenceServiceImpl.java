package ru.sgu.csit.inoc.deansoffice.services.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.ReferenceDAO;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.reports.ReportPdfProcessor;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.Report;
import ru.sgu.csit.inoc.deansoffice.services.ReferenceService;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:37
 */
//@Service
public class ReferenceServiceImpl extends DocumentServiceImpl implements ReferenceService {
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private static ReferenceDAO referenceDAO = applicationContext.getBean(ReferenceDAO.class);

    public ReferenceServiceImpl(Document document) {
        super(document);
    }

    public void build(Student student) {
        clear();
        TEXT.put("FACULTY_FULLNAME", student.getSpeciality().getFaculty().getFullName());
        TEXT.put("FACULTY_SHORTNAME", student.getSpeciality().getFaculty().getShortName());

        TEXT.put("FACULTY_DEAN", student.getSpeciality().getFaculty().getDean().generateShortName(true));

        TEXT.put("RECTOR", student.getSpeciality().getFaculty().getRector().generateShortName(true));
        TEXT.put("RECTOR_DEGREE", student.getSpeciality().getFaculty().getRector().getDegree());

        TEXT.put("Student.fullName_dat", student.getLastNameDative() + " "
                + student.getFirstNameDative() + " " + student.getMiddleNameDative());
        TEXT.put("Student.lastName_dat", student.getLastNameDative());
        TEXT.put("Student.firstName_dat", student.getFirstNameDative());
        TEXT.put("Student.middleName_dat", student.getMiddleNameDative());

        TEXT.put("Student.courseNumber", student.getCourse().toString());

        EnrollmentOrder order = student.getEnrollmentOrder();
        String division = "неизвестного";

        switch (student.getDivision()) {
            case INTRAMURAL:
                division = "очного";
                break;
            case EXTRAMURAL:
                division = "заочного";
                break;
            case EVENINGSTUDY:
                division = "вечернего";
                break;
        }
        TEXT.put("Student.division_rad", division);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = order.getEnrollmentDate();

        TEXT.put("Student.startDate", dateFormat.format(date)); // "01.09.2007"

        date = order.getReleaseDate();
        TEXT.put("Student.endDate", dateFormat.format(date)); // "01.07.2012"
        TEXT.put("Student.order.number", order.getNumber()); // "22-0107"
        TEXT.put("Student.order.date", dateFormat.format(order.getSignedDate())); // "12.08.2007"

        String studyForm = "неизвестная";

        switch (student.getStudyForm()) {
            case BUDGET:
                studyForm = "бюджетная";
                break;
            case COMMERCIAL:
                studyForm = "коммерческая";
                break;
        }
        TEXT.put("Student.studyForm", studyForm);
    }

    @Override
    public void addNewReference(Reference reference) {
        reference.setAddedDate(new Date());
        reference.setState(Reference.ReferenceState.ADDED);
        referenceDAO.saveOrUpdate(reference);
    }

    @Override
    public void generatePrintForm(List<Reference> references, OutputStream outputStream) {
        String documentName = "";
        String templName = ReferenceServiceImpl.class.getResource("/templates/reference-1.xml").getFile();
        templName = templName.replace("%20", " ");

        for (Reference reference : references) {
            Student theStudent = stu
            System.out.println(templName);
            reference.setPrintTemplate(new Template(templName));
            ReferenceService referenceService = new ReferenceServiceImpl(reference);
            referenceService.build(theStudent);
            references.add((Report) referenceService);
        }

        ReportPdfProcessor.getInstance().generate(references, outputStream);
    }
}
