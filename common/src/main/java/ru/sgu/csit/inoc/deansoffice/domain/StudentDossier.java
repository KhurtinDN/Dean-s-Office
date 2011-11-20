package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * This c document contains dossier of student.
 */
@Entity
@Table(name = "student_dossier")
public class StudentDossier extends Document {
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .toString();
    }
}
