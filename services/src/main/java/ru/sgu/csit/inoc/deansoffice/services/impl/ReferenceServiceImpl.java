package ru.sgu.csit.inoc.deansoffice.services.impl;

import ru.sgu.csit.inoc.deansoffice.domain.Document;
import ru.sgu.csit.inoc.deansoffice.domain.EnrollmentOrder;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.services.ReferenceService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:37
 */
public class ReferenceServiceImpl extends DocumentServiceImpl implements ReferenceService {
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

        TEXT.put("Student.courseNumber", student.getCource().toString());

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
}
