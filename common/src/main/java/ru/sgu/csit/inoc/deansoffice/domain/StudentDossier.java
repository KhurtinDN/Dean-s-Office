package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 27.12.10
 * Time: 10:56
 */
@Entity
public class StudentDossier extends Document {
    public void build(Student student) {
        clear();
        TEXT.put("FACULTY_FULLNAME", student.getSpeciality().getFaculty().getFullName());
        TEXT.put("FACULTY_SHORTNAME", student.getSpeciality().getFaculty().getShortName());

        TEXT.put("FACULTY_DEAN", student.getSpeciality().getFaculty().getDean().generateShortName(true));

        TEXT.put("RECTOR", student.getSpeciality().getFaculty().getRector().generateShortName(true));
        TEXT.put("RECTOR_DEGREE", student.getSpeciality().getFaculty().getRector().getDegree());

        TEXT.put("Student.fullName", student.getLastName() + " "
                + student.getFirstName() + " " + student.getMiddleName());
        TEXT.put("Student.lastName", student.getLastName());
        TEXT.put("Student.firstName", student.getFirstName());
        TEXT.put("Student.middleName", student.getMiddleName());

        TEXT.put("Student.courseNumber", student.getCource().toString());
        TEXT.put("Student.speciality", student.getSpeciality().getShortName());
        TEXT.put("Student.specialityCode", student.getSpeciality().getCode());

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
        SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        Date date = order.getEnrollmentDate();

        // Борьба с ленивой загрузкой
        // http://forum.vingrad.ru/forum/topic-258355.html
        // http://www.javalobby.org/java/forums/t62077.html
        // .initialize(student.getAdditionalData());
        //Hibernate.initialize(student.getAdditionalData().getPhoto());

        if (student.getAdditionalData().getBirthPlace() == null) {
            throw new RuntimeException("AdditionalData.BirthPlace is null!!! ");
        }
        TEXT.put("Student.birthday", dateFormat.format(student.getAdditionalData().getBirthday()));
        TEXT.put("Student.birthPlace", student.getAdditionalData().getBirthPlace());

        System.out.println(student.getAdditionalData().getPhoto().getData().length);
        TEXT.put("Student.photoData", student.getAdditionalData().getPhoto().getData());

        TEXT.put("Student.startDate", dateFormat.format(date)); // "01.09.2007"
        TEXT.put("Student.startYear", dateFormatYear.format(date));

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
