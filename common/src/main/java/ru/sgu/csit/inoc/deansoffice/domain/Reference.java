package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by XX (MesheryakovAV)
 * Date: 07.09.2010
 * Time: 9:18:30
 */
@Entity
public class Reference extends Document {
    public void build(Student student) {
        Reference reference = this; // Заглушка

        reference.clear();
        reference.TEXT.put("FACULTY_FULLNAME", student.getSpeciality().getFaculty().getFullName());
        reference.TEXT.put("FACULTY_SHORTNAME", student.getSpeciality().getFaculty().getShortName());

        reference.TEXT.put("FACULTY_DEAN", student.getSpeciality().getFaculty().getDean().generateShortName(true));

        reference.TEXT.put("RECTOR", student.getSpeciality().getFaculty().getRector().generateShortName(true));
        reference.TEXT.put("RECTOR_DEGREE", student.getSpeciality().getFaculty().getRector().getDegree());

        reference.TEXT.put("Student.fullName_dat", student.getLastNameDative() + " "
                + student.getFirstNameDative() + " " + student.getMiddleNameDative());
        reference.TEXT.put("Student.lastName_dat", student.getLastNameDative());
        reference.TEXT.put("Student.firstName_dat", student.getFirstNameDative());
        reference.TEXT.put("Student.middleName_dat", student.getMiddleNameDative());
        
        reference.TEXT.put("Student.courseNumber", student.getCource().toString());

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
        reference.TEXT.put("Student.division_rad", division);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date date = order.getEnrollmentDate();

        reference.TEXT.put("Student.startDate", dateFormat.format(date)); // "01.09.2007"

        date = order.getReleaseDate();
        reference.TEXT.put("Student.endDate", dateFormat.format(date)); // "01.07.2012"
        reference.TEXT.put("Student.order.number", order.getNumber()); // "22-0107"
        reference.TEXT.put("Student.order.date", dateFormat.format(order.getSignedDate())); // "12.08.2007"

        String studyForm = "неизвестная";

        switch (student.getStudyForm()) {
            case BUDGET:
                studyForm = "бюджетная";
                break;
            case COMMERCIAL:
                studyForm = "коммерческая";
                break;
        }
        reference.TEXT.put("Student.studyForm", studyForm);
    }
}
