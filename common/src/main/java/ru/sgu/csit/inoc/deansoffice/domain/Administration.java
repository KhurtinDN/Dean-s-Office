package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * @author Denis Khurtin
 */
@Entity
public class Administration extends PersistentItem {

    private String name;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Employee rector;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Employee getRector() {
        return rector;
    }

    public void setRector(Employee rector) {
        this.rector = rector;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Administration that = (Administration) o;

        return super.equals(that) &&
                Objects.equal(this.name, that.name) &&
                Objects.equal(this.rector, that.rector);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                name,
                rector);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("name", name)
                .add("rector", rector)
                .toString();
    }

    public static Administration make(
            final Long id,
            final String name,
            final Employee rector) {

        final Administration administration = new Administration();
        administration.setId(id);
        administration.setName(name);
        administration.setRector(rector);

        return administration;
    }
}
