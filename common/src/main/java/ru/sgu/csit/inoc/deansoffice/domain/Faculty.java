package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * The faculty.
 */
@Entity
@Table(name = "faculty")
public class Faculty extends PersistentItem {
    private String fullName;
    private String shortName;
                         
    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Employee dean;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Administration administration;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(final String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(final String shortName) {
        this.shortName = shortName;
    }

    public Employee getDean() {
        return dean;
    }

    public void setDean(final Employee dean) {
        this.dean = dean;
    }

    public Administration getAdministration() {
        return administration;
    }

    public void setAdministration(final Administration administration) {
        this.administration = administration;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Faculty that = (Faculty) o;

        return super.equals(that) &&
                Objects.equal(this.fullName, that.fullName) &&
                Objects.equal(this.shortName, that.shortName) &&
                Objects.equal(this.dean, that.dean) &&
                Objects.equal(this.administration, that.administration);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                fullName,
                shortName,
                dean,
                administration);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("fullName", fullName)
                .add("shortName", shortName)
                .add("dean", dean)
                .add("administration", administration)
                .toString();
    }
}
