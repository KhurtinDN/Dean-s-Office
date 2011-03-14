package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
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

    @Entity
    public static class SourceData extends DirectiveSourceData {
        @ElementCollection(fetch = FetchType.EAGER)
        private Map<Group, Student> captains = new HashMap<Group, Student>();

        public void addCaptain(Group group, Student student) {
            captains.put(group, student);
        }

        public Student removeCaptain(Group group) {
            return captains.remove(group);
        }

        public Student getCaptain(Group group) {
            return captains.get(group);
        }

        @ElementCollection
        public Map<Group, Student> getCaptains() {
            return captains;
        }

        public void setCaptains(Map<Group, Student> captains) {
            this.captains = captains;
        }
    }
}
