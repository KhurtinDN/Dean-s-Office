package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 04.03.11
 * Time: 10:31
 */
@Entity
public class Stipend extends PersistentItem {
    private StipendType type;
    private Date startDate;
    private Date endDate;
    private Integer value;

    public StipendType getType() {
        return type;
    }

    public void setType(StipendType type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static enum StipendType {
        SOCIAL, REGULAR, HEIGHTENED
    }
}
