package ru.sgu.csit.inoc.deansoffice.services.impl;

import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.services.StudentDossierService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:16
 */
public class StudentDossierServiceImpl extends DocumentServiceImpl implements StudentDossierService {
    public StudentDossierServiceImpl(Document document) {
        super(document);
    }

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

        TEXT.put("Student.courseNumber", student.getCourse().toString());
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

        TEXT.put("Student.birthday", dateFormat.format(student.getBirthday()));
        TEXT.put("Student.birthPlace", student.getAdditionalData().getBirthPlace());

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

        String str = "";

        switch (student.getSex()) {
            case MALE:
                str = "М";
                break;
            case FEMALE:
                str = "Ж";
                break;
        }
        TEXT.put("Student.sex", str);

        TEXT.put("Student.education", student.getAdditionalData().getEducation());
        TEXT.put("Student.workInfo", student.getAdditionalData().getWorkInfo());
        str = student.getAdditionalData().getMaritalStatus();
        TEXT.put("Student.maritalStatus", str == null ? "\n" : str);
        str = student.getAdditionalData().getChildrenInfo();
        TEXT.put("Student.childrenInfo", str == null ? "\n" : str);

        Passport firstPassport = student.getAdditionalData().getPassports().get(0);
        Passport lastPassport;

        if (student.getAdditionalData().getPassports().size() > 1) {
            lastPassport = student.getAdditionalData().getPassports().get(
                    student.getAdditionalData().getPassports().size() - 1);
            TEXT.put("Student.citizenship", lastPassport.getCitizenship());
        } else {
            lastPassport = new Passport();
            new PassportServiceImpl().fillAllFields(lastPassport, "");
            TEXT.put("Student.citizenship", firstPassport.getCitizenship());
        }

        TEXT.put("Student.firstPassport.fullName", firstPassport.getLastName() + " "
                + firstPassport.getFirstName() + " " + firstPassport.getMiddleName());
        TEXT.put("Student.firstPassport.citizenship", firstPassport.getCitizenship());
        TEXT.put("Student.firstPassport.number", firstPassport.getNumber());
        TEXT.put("Student.firstPassport.series", firstPassport.getSeries());
        TEXT.put("Student.firstPassport.issuedDate", dateFormat.format(firstPassport.getIssuedDate()));
        TEXT.put("Student.firstPassport.issuingOrganization", firstPassport.getIssuingOrganization());

        TEXT.put("Student.lastPassport.fullName", lastPassport.getLastName() + " "
                + lastPassport.getFirstName() + " " + lastPassport.getMiddleName());
        TEXT.put("Student.lastPassport.citizenship", lastPassport.getCitizenship());
        TEXT.put("Student.lastPassport.number", lastPassport.getNumber());
        TEXT.put("Student.lastPassport.series", lastPassport.getSeries());
        TEXT.put("Student.lastPassport.issuedDate", lastPassport.getIssuedDate() == null ? "" :
                dateFormat.format(lastPassport.getIssuedDate()));
        TEXT.put("Student.lastPassport.issuingOrganization", lastPassport.getIssuingOrganization());

        Parent father = student.getAdditionalData().getFather();
        Parent mather = student.getAdditionalData().getMather();

        TEXT.put("Student.father.fullName", father.getLastName() + " "
                + father.getFirstName() + " " + father.getMiddleName());
        TEXT.put("Student.father.birthday", dateFormat.format(father.getBirthday()));
        TEXT.put("Student.father.address", father.getAddress());
        TEXT.put("Student.father.workInfo",  father.getWorkInfo());
        TEXT.put("Student.father.phoneNumbers", father.getPhoneNumbers());

        TEXT.put("Student.mather.fullName", mather.getLastName() + " "
                + mather.getFirstName() + " " + mather.getMiddleName());
        TEXT.put("Student.mather.birthday", dateFormat.format(mather.getBirthday()));
        TEXT.put("Student.mather.address", mather.getAddress());
        TEXT.put("Student.mather.workInfo",  mather.getWorkInfo());
        TEXT.put("Student.mather.phoneNumbers", mather.getPhoneNumbers());

        TEXT.put("Student.oldAddress", student.getAdditionalData().getOldAddress());
        TEXT.put("Student.actualAddress", student.getAdditionalData().getActualAddress());
        TEXT.put("Student.passportAddress", student.getAdditionalData().getPassports().size() > 1 ?
                lastPassport.getAddress() : firstPassport.getAddress());
    }
}
