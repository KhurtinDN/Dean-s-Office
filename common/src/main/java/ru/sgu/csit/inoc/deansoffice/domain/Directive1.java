package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.HashMap;
import java.util.Map;

/**
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 12:41
 */
@Entity
public class Directive1 extends Directive {
    public Directive1() {
        type = APPOINT_CAPTAINS;

        //data.setDescription("Следующих студентов 1 курса дневного отделения " +
        //        "факультета КНиИТ разделить по группам следующим образом.");
        data.setDescription("Следующих студентов 1 курса назначить старостами групп.");
        data.setGrounds("Представление декана с резолюцией проректора по УОР.");
        //data.setSourceData(new SourceData());
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .toString();
    }

    public static Directive1 make(
            final Long id,
            final DirectiveData data,
            final String type) {

        final Directive1 directive1 = new Directive1();
        directive1.setId(id);
        directive1.setData(data);
        directive1.setType(type);

        return directive1;
    }

    @Entity
    public static class SourceData extends DirectiveSourceData {
        @ElementCollection(fetch = FetchType.EAGER)
        private Map<Group, Student> captains = new HashMap<Group, Student>();

        public void addCaptain(Group group, Student student) {
            captains.put(group, student);
        }

        public Student removeCaptain(final Group group) {
            return captains.remove(group);
        }

        public Student getCaptain(final Group group) {
            return captains.get(group);
        }

        @ElementCollection
        public Map<Group, Student> getCaptains() {
            return captains;
        }

        public void setCaptains(final Map<Group, Student> captains) {
            this.captains = captains;
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
                    Objects.equal(this.captains, that.captains);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(
                    super.hashCode(),
                    captains);
        }

        @Override
        public String toString() {
            return Objects.toStringHelper(this)
                    .addValue(super.toString())
                    .add("captains", captains)
                    .toString();
        }

        public static SourceData make(
                final Long id,
                final Map<Group, Student> captains) {

            final SourceData sourceData = new SourceData();
            sourceData.setId(id);
            sourceData.setCaptains(captains);

            return sourceData;
        }
    }
}
