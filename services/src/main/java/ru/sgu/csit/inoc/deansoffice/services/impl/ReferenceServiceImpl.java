package ru.sgu.csit.inoc.deansoffice.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.ReferenceDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.EnrollmentOrder;
import ru.sgu.csit.inoc.deansoffice.domain.Reference;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.domain.Template;
import ru.sgu.csit.inoc.deansoffice.reports.ReportPdfProcessor;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.Report;
import ru.sgu.csit.inoc.deansoffice.reports.reportsutil.ReportXml;
import ru.sgu.csit.inoc.deansoffice.services.ReferenceService;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:37
 */
@Service
public class ReferenceServiceImpl extends DocumentServiceImpl implements ReferenceService {
    @Autowired
    private ReferenceDAO referenceDAO;
    @Autowired
    private StudentDAO studentDAO;

    public ReferenceServiceImpl() {
    }

    private void build(ReportXml report, Student student, String purpose) {
        if (purpose == null) {
            purpose = "";
        }
        putDefaultValues(report.getValuesMap());
        report.addValue("FACULTY_FULLNAME", student.getSpeciality().getFaculty().getFullName());
        report.addValue("FACULTY_SHORTNAME", student.getSpeciality().getFaculty().getShortName());

        report.addValue("FACULTY_DEAN", student.getSpeciality().getFaculty().getDean().generateShortName(true));

        report.addValue("RECTOR", student.getSpeciality().getFaculty().getRector().generateShortName(true));
        report.addValue("RECTOR_DEGREE", student.getSpeciality().getFaculty().getRector().getDegree());

        report.addValue("Student.fullName_dat", student.getLastNameDative() + " "
                + student.getFirstNameDative() + " " + student.getMiddleNameDative());
        report.addValue("Student.lastName_dat", student.getLastNameDative());
        report.addValue("Student.firstName_dat", student.getFirstNameDative());
        report.addValue("Student.middleName_dat", student.getMiddleNameDative());

        report.addValue("Student.courseNumber", student.getGroup().getCourse().toString());

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
        Date date = order.getEnrollmentDate();

        report.addValue("Student.startDate", dateFormat.format(date)); // "01.09.2007"

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
        report.addValue("Purpose", purpose);
    }

    @Override
    public void generatePrintForm(Reference reference, OutputStream outputStream) {
        ReportXml reportXml = new ReportXml();
        if (reference.getPrintTemplate() == null ||
                reference.getPrintTemplate().getFileName() == null) {
            setDefaultPrintTemplate(reference);
        }
        reportXml.setTemplateFileName(reference.getPrintTemplate().getFileName());
        build(reportXml, studentDAO.findById(reference.getOwnerId()), reference.getPurpose());
        ReportPdfProcessor.getInstance().generate(reportXml, outputStream);
    }

    @Override
    public void generatePrintForm(List<Reference> references, OutputStream outputStream) {
        List<Report> reports = new ArrayList<Report>();

        for (Reference reference : references) {
            ReportXml reportXml = new ReportXml();
            if (reference.getPrintTemplate() == null ||
                    reference.getPrintTemplate().getFileName() == null) {
                setDefaultPrintTemplate(reference);
            }
            reportXml.setTemplateFileName(reference.getPrintTemplate().getFileName());
            build(reportXml, studentDAO.findById(reference.getOwnerId()), reference.getPurpose());
            reports.add(reportXml);
        }
        ReportPdfProcessor.getInstance().generate(reports, outputStream);
    }

    @Override
    public void setDefaultPrintTemplate(Reference reference) {
        String documentName;

        switch (reference.getType()) {
            case REFERENCE_1:
                documentName = "reference-1.xml";
                break;
            case REFERENCE_2:
                documentName = "reference-2.xml";
                break;
            case REFERENCE_3:
                documentName = "reference-3.xml";
                break;
            default:
                throw new RuntimeException("Unknown template file name for reference type " +
                        reference.getType() + ".");
        }
        String templName = ReferenceServiceImpl.class.getResource("/templates/" + documentName).getFile();
        templName = templName.replace("%20", " ");
        reference.setPrintTemplate(new Template(templName));
    }

    @Override
    public Reference makeReference(Reference.ReferenceType type, Long ownerId) {
        Reference reference = new Reference();
        reference.setType(type);
        reference.setOwnerId(ownerId);
        return reference;
    }

    @Override
    public void registrationReference(Reference reference) {
        reference.setRegisteredDate(new Date());
        reference.setState(Reference.ReferenceState.REGISTERED);
        referenceDAO.saveOrUpdate(reference);
    }

    @Override
    public void printReferencesById(List<Long> referenceIds) {
        for (Long referenceId : referenceIds) {
            Reference reference = referenceDAO.findById(referenceId);
            reference.setState(Reference.ReferenceState.PROCESSED);
            referenceDAO.update(reference);
        }
    }

    @Override
    public void readyReferencesById(List<Long> referenceIds) {
        for (Long referenceId : referenceIds) {
            Reference reference = referenceDAO.findById(referenceId);
            reference.setState(Reference.ReferenceState.READY);
            referenceDAO.update(reference);
        }
    }

    @Override
    public void issueReferencesById(List<Long> referenceIds) {
        for (Long referenceId : referenceIds) {
            Reference reference = referenceDAO.findById(referenceId);
            reference.setIssuedDate(new Date());
            reference.setState(Reference.ReferenceState.ISSUED);
            referenceDAO.update(reference);
        }
    }

    @Override
    public void removeReferencesById(List<Long> referenceIds) {
        for (Long referenceId : referenceIds) {
            referenceDAO.deleteById(referenceId);
        }
    }
}