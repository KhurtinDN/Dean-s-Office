package ru.sgu.csit.inoc.deansoffice.services;

import ru.sgu.csit.inoc.deansoffice.domain.Reference;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

import java.io.OutputStream;
import java.util.List;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 13.01.11
 * Time: 10:36
 */
public interface ReferenceService {
    void generatePrintForm(Reference reference, OutputStream outputStream);
    void generatePrintForm(List<Reference> references, OutputStream outputStream);
    void setDefaultPrintTemplate(Reference reference);

    Reference makeReference(Reference.ReferenceType type, Long ownerId);
    void registrationReference(Reference reference);
    void printReferencesById(List<Long> referenceIds);
    void readyReferencesById(List<Long> referenceIds);
    void issueReferencesById(List<Long> referenceIds);

    void removeReferencesById(List<Long> referenceIds);
}
