package ru.sgu.csit.inoc.deansoffice.services;

import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.domain.StudentDossier;

import java.io.OutputStream;
import java.util.List;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:14
 */
public interface StudentDossierService {
    void generatePrintForm(StudentDossier dossier, Student student, OutputStream outputStream);
    void generatePrintForm(List<StudentDossier> dossiers, Student student, OutputStream outputStream);
    void setDefaultPrintTemplate(StudentDossier dossier);
}
