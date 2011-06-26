package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import java.util.Date;

/**
 * User: XX (freecoder.xx@gmail.com)
 * Date: 27.12.10
 * Time: 10:56
 */
@Entity
public class StudentDossier extends Document {
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .toString();
    }

    public static StudentDossier make(
            final Long id,
            final String number,
            final Date signedDate,
            final Template printTemplate) {

        final StudentDossier studentDossier = new StudentDossier();
        studentDossier.setId(id);
        studentDossier.setNumber(number);
        studentDossier.setSignedDate(signedDate);
        studentDossier.setPrintTemplate(printTemplate);

        return studentDossier;
    }
}
