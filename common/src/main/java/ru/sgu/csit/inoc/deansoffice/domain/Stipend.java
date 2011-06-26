package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import java.util.Date;

/**
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

    public void setType(final StipendType type) {
        this.type = type;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(final Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(final Date endDate) {
        this.endDate = endDate;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(final Integer value) {
        this.value = value;
    }

    public static enum StipendType {
        SOCIAL, REGULAR, HEIGHTENED
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Stipend that = (Stipend) o;

        return super.equals(that) &&
                Objects.equal(this.type, that.type) &&
                Objects.equal(this.startDate, that.startDate) &&
                Objects.equal(this.endDate, that.endDate) &&
                Objects.equal(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                type,
                startDate,
                endDate,
                value);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("type", type)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .add("value", value)
                .toString();
    }

    public static Stipend make(
            final Long id,
            final StipendType type,
            final Date startDate,
            final Date endDate,
            final Integer value) {

        final Stipend stipend = new Stipend();
        stipend.setId(id);
        stipend.setType(type);
        stipend.setStartDate(startDate);
        stipend.setEndDate(endDate);
        stipend.setValue(value);

        return stipend;
    }
}
