package ru.sgu.csit.inoc.deansoffice.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by XX (MesheryakovAV)
 * Date: 07.09.2010
 * Time: 9:18:30
 */
public class Reference extends Document {    
    public void build(Student student) {
        clear();
        TEXT.put("FACULTY_FULLNAME", student.getSpeciality().getFaculty().getFullName());
        TEXT.put("FACULTY_SHORTNAME", student.getSpeciality().getFaculty().getShortName());

        TEXT.put("FACULTY_DEAN", student.getSpeciality().getFaculty().getDean().generateShortName(true));

        TEXT.put("Student.fullName_dat", student.getLastNameDative() + " "
                + student.getFirstNameDative() + " " + student.getMiddleNameDative());
        TEXT.put("Student.courseNumber", student.getCource().toString());

        Order order = student.getEnrollmentOrder();
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
        Date date = (Date)order.getData("EnrollmentDate");

        TEXT.put("Student.startDate", dateFormat.format(date)); // "01.09.2007"

        date = (Date)order.getData("ReleaseDate");
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
