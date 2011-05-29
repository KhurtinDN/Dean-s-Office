package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 11:59:29 AM
 */
@Entity
public class Faculty extends PersistentItem {
    private String fullName;
    private String shortName;
                         
    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Dean dean;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Rector rector;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public Dean getDean() {
        return dean;
    }

    public void setDean(Dean dean) {
        this.dean = dean;
    }

    public Rector getRector() {
        return rector;
    }

    public void setRector(Rector rector) {
        this.rector = rector;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Faculty that = (Faculty) o;

        return super.equals(that) &&
                Objects.equal(this.fullName, that.fullName) &&
                Objects.equal(this.shortName, that.shortName) &&
                Objects.equal(this.dean, that.dean) &&
                Objects.equal(this.rector, that.rector);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                fullName,
                shortName,
                dean,
                rector);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("fullName", fullName)
                .add("shortName", shortName)
                .add("dean", dean)
                .add("rector", rector)
                .toString();
    }
}
