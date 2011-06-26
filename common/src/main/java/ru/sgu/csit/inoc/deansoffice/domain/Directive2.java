package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.HashMap;
import java.util.Map;

/**
 * User: MesheryakovAV
 * Date: 04.03.11
 * Time: 10:38
 */
@Entity
public class Directive2 extends Directive {
    public Directive2() {
        type = APPOINT_SOCIAL_STIPEND;

        data.setDescription("Следующим студентам факультета назначить выплату социальной стипендии");
        data.setGrounds("справки УСЗН, решение стипендиальной комиссии");
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .toString();
    }

    public static Directive2 make(
            final Long id,
            final DirectiveData data,
            final String type) {

        final Directive2 directive2 = new Directive2();
        directive2.setId(id);
        directive2.setData(data);
        directive2.setType(type);

        return directive2;
    }

    @Entity
    public static class SourceData extends DirectiveSourceData {
        @ElementCollection(fetch = FetchType.EAGER)
        private Map<Student, Stipend> students = new HashMap<Student, Stipend>();

        public void addStudent(final Student student, final Stipend stipend) {
            students.put(student, stipend);
        }

        public Stipend removeStudent(final Student student) {
            return students.remove(student);
        }

        public Stipend getStipend(final Student student) {
            return students.get(student);
        }

        @ElementCollection
        public Map<Student, Stipend> getStudents() {
            return students;
        }

        public void setStudents(final Map<Student, Stipend> students) {
            this.students = students;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            final SourceData that = (SourceData) o;

            return super.equals(that) &&
                    Objects.equal(this.students, that.students);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(
                    super.hashCode(),
                    students);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .addValue(super.toString())
                    .add("students", students)
                    .toString();
        }
    }
}
