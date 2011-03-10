package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 04.03.11
 * Time: 10:38
 */
@Entity
public class Directive2 extends Directive {
    public Directive2() {
        type = APPOINT_SOCIAL_STIPEND;
        data = new DirectiveData();

        data.setDescription("Следующим студентам факультета назначить выплату социальной стипендии");
        data.setGrounds("справки УСЗН, решение стипендиальной комиссии");
    }

    @Entity
    public static class SourceData extends DirectiveSourceData {
        @ElementCollection(fetch = FetchType.EAGER)
        private Map<Student, Stipend> students = new HashMap<Student, Stipend>();

        public void addStudent(Student student, Stipend stipend) {
            students.put(student, stipend);
        }

        public Stipend removeStudent(Student student) {
            return students.remove(student);
        }

        public Stipend getStipend(Student student) {
            return students.get(student);
        }

        @ElementCollection
        public Map<Student, Stipend> getStudents() {
            return students;
        }

        public void setStudents(Map<Student, Stipend> students) {
            this.students = students;
        }
    }
}
