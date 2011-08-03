package ru.sgu.csit.inoc.deansoffice.services.impl;

import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.reports.ReportPdfProcessor;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.Report;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.ReportXml;
import ru.sgu.csit.inoc.deansoffice.services.StudentDossierService;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:16
 */
@Service
public class StudentDossierServiceImpl extends DocumentServiceImpl implements StudentDossierService {
    private void build(ReportXml report, Student student) {
        putDefaultValues(report.getValuesMap());
        report.addValue("FACULTY_FULLNAME", student.getSpeciality().getFaculty().getFullName());
        report.addValue("FACULTY_SHORTNAME", student.getSpeciality().getFaculty().getShortName());

        report.addValue("FACULTY_DEAN", student.getSpeciality().getFaculty().getDean().generateShortName(true));

        report.addValue("RECTOR", student.getSpeciality().getFaculty().getAdministration().getRector().generateShortName(true));
        report.addValue("RECTOR_DEGREE", student.getSpeciality().getFaculty().getAdministration().getRector().getDegree());

        report.addValue("Student.fullName", student.getLastName() + " "
                + student.getFirstName() + " " + student.getMiddleName());
        report.addValue("Student.lastName", student.getLastName());
        report.addValue("Student.firstName", student.getFirstName());
        report.addValue("Student.middleName", student.getMiddleName());

        report.addValue("Student.courseNumber", student.getGroup().getCourse().toString());
        report.addValue("Student.speciality", student.getSpeciality().getShortName());
        report.addValue("Student.specialityCode", student.getSpeciality().getCode());

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
        report.addValue("Student.division_rad", division);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat dateFormatYear = new SimpleDateFormat("yyyy");
        Date date = order.getEnrollmentDate();

        // Борьба с ленивой загрузкой
        // http://forum.vingrad.ru/forum/topic-258355.html
        // http://www.javalobby.org/java/forums/t62077.html
        // .initialize(student.getAdditionalData());
        //Hibernate.initialize(student.getAdditionalData().getPhoto());

        report.addValue("Student.birthday", dateFormat.format(student.getBirthday()));
        report.addValue("Student.birthPlace", student.getAdditionalData().getBirthPlace());

        report.addValue("Student.photoData", student.getAdditionalData().getPhoto().getData());

        report.addValue("Student.startDate", dateFormat.format(date)); // "01.09.2007"
        report.addValue("Student.startYear", dateFormatYear.format(date));

        date = order.getReleaseDate();
        report.addValue("Student.endDate", dateFormat.format(date)); // "01.07.2012"
        report.addValue("Student.order.number", order.getNumber()); // "22-0107"
        report.addValue("Student.order.date", dateFormat.format(order.getSignedDate())); // "12.08.2007"

        String studyForm = "неизвестная";

        switch (student.getStudyForm()) {
            case BUDGET:
                studyForm = "бюджетная";
                break;
            case COMMERCIAL:
                studyForm = "коммерческая";
                break;
        }
        report.addValue("Student.studyForm", studyForm);

        String str = "";

        switch (student.getSex()) {
            case MALE:
                str = "М";
                break;
            case FEMALE:
                str = "Ж";
                break;
        }
        report.addValue("Student.sex", str);

        report.addValue("Student.education", student.getAdditionalData().getEducation());
        report.addValue("Student.workInfo", student.getAdditionalData().getWorkInfo());
        str = student.getAdditionalData().getMaritalStatus();
        report.addValue("Student.maritalStatus", str == null ? "\n" : str);
        str = student.getAdditionalData().getChildrenInfo();
        report.addValue("Student.childrenInfo", str == null ? "\n" : str);

        Passport firstPassport = student.getAdditionalData().getPassports().get(0);
        Passport lastPassport;
        /*Passport actualPasport = null;

        for (Passport currentPassport : student.getAdditionalData().getPassports()) {
            if (currentPassport.isActual()) {
                actualPasport = currentPassport;
                break;
            }
        }
        report.addValue("Student.citizenship", actualPasport.getCitizenship());
        */
        if (student.getAdditionalData().getPassports().size() > 1) {
            lastPassport = student.getAdditionalData().getPassports().get(
                    student.getAdditionalData().getPassports().size() - 1);
            report.addValue("Student.citizenship", lastPassport.getCitizenship());
        } else {
            lastPassport = new Passport();
            new PassportServiceImpl().fillAllFields(lastPassport, "");
            report.addValue("Student.citizenship", firstPassport.getCitizenship());
        }

        report.addValue("Student.firstPassport.fullName", firstPassport.getLastName() + " "
                + firstPassport.getFirstName() + " " + firstPassport.getMiddleName());
        report.addValue("Student.firstPassport.citizenship", firstPassport.getCitizenship());
        report.addValue("Student.firstPassport.number", firstPassport.getNumber());
        report.addValue("Student.firstPassport.series", firstPassport.getSeries());
        report.addValue("Student.firstPassport.issuedDate", dateFormat.format(firstPassport.getIssuedDate()));
        report.addValue("Student.firstPassport.issuingOrganization", firstPassport.getIssuingOrganization());

        report.addValue("Student.lastPassport.fullName", lastPassport.getLastName() + " "
                + lastPassport.getFirstName() + " " + lastPassport.getMiddleName());
        report.addValue("Student.lastPassport.citizenship", lastPassport.getCitizenship());
        report.addValue("Student.lastPassport.number", lastPassport.getNumber());
        report.addValue("Student.lastPassport.series", lastPassport.getSeries());
        report.addValue("Student.lastPassport.issuedDate", lastPassport.getIssuedDate() == null ? "" :
                dateFormat.format(lastPassport.getIssuedDate()));
        report.addValue("Student.lastPassport.issuingOrganization", lastPassport.getIssuingOrganization());

        Parent father = student.getAdditionalData().getFather();
        Parent mother = student.getAdditionalData().getMother();

        report.addValue("Student.father.fullName", father.getLastName() + " "
                + father.getFirstName() + " " + father.getMiddleName());
        report.addValue("Student.father.birthday", dateFormat.format(father.getBirthday()));
        report.addValue("Student.father.address", father.getAddress());
        report.addValue("Student.father.workInfo", father.getWorkInfo());
        report.addValue("Student.father.phoneNumbers", father.getPhoneNumbers());

        report.addValue("Student.mother.fullName", mother.getLastName() + " "
                + mother.getFirstName() + " " + mother.getMiddleName());
        report.addValue("Student.mother.birthday", dateFormat.format(mother.getBirthday()));
        report.addValue("Student.mother.address", mother.getAddress());
        report.addValue("Student.mother.workInfo", mother.getWorkInfo());
        report.addValue("Student.mother.phoneNumbers", mother.getPhoneNumbers());

        report.addValue("Student.oldAddress", student.getAdditionalData().getOldAddress());
        report.addValue("Student.actualAddress", student.getAdditionalData().getActualAddress());
        report.addValue("Student.passportAddress", student.getAdditionalData().getPassports().size() > 1 ?
                lastPassport.getAddress() : firstPassport.getAddress());
    }

    @Override
    public void generatePrintForm(StudentDossier dossier, Student student, OutputStream outputStream) {
        ReportXml reportXml = new ReportXml();
        if (dossier.getPrintTemplate() == null ||
                dossier.getPrintTemplate().getFileName() == null) {
            setDefaultPrintTemplate(dossier);
        }
        reportXml.setTemplateFileName(dossier.getPrintTemplate().getFileName());
        build(reportXml, student);
        ReportPdfProcessor.getInstance().generate(reportXml, outputStream);
    }

    @Override
    public void generatePrintForm(List<StudentDossier> dossiers, Student student, OutputStream outputStream) {
        List<Report> reports = new ArrayList<Report>();

        for (StudentDossier dossier : dossiers) {
            ReportXml reportXml = new ReportXml();
            if (dossier.getPrintTemplate() == null ||
                    dossier.getPrintTemplate().getFileName() == null) {
                setDefaultPrintTemplate(dossier);
            }
            reportXml.setTemplateFileName(dossier.getPrintTemplate().getFileName());
            build(reportXml, student);
            reports.add(reportXml);
        }
        ReportPdfProcessor.getInstance().generate(reports, outputStream);
    }

    @Override
    public void setDefaultPrintTemplate(StudentDossier dossier) {
        String templName = StudentDossierServiceImpl.class.getResource("/templates/dossier.xml").getFile();
        templName = templName.replace("%20", " ");
        System.out.println(templName);
        dossier.setPrintTemplate(new Template(templName));
    }
}
