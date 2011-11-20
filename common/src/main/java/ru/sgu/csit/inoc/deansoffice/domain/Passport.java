package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;

/**
 * The passport.
 */
@Entity
@Table(name = "passport")
public class Passport extends Person {
    private boolean actual;
    private String series;
    private String number;
    private String issuingOrganization;
    private Date issuedDate;
    private String citizenship;
    private String address;

    public Passport() {
    }

    public Passport(final Person person) {
        setSex(person.getSex());

        setFirstName(person.getFirstName());
        setMiddleName(person.getMiddleName());
        setLastName(person.getLastName());

        setFirstNameDative(person.getFirstNameDative());
        setMiddleNameDative(person.getMiddleNameDative());
        setLastNameDative(person.getLastNameDative());
    }

    public boolean isActual() {
        return actual;
    }

    public void setActual(final boolean actual) {
        this.actual = actual;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(final String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(final String number) {
        this.number = number;
    }

    public String getIssuingOrganization() {
        return issuingOrganization;
    }

    public void setIssuingOrganization(final String issuingOrganization) {
        this.issuingOrganization = issuingOrganization;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(final Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(final String citizenship) {
        this.citizenship = citizenship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Passport that = (Passport) o;

        return super.equals(that) &&
                Objects.equal(this.actual, that.actual) &&
                Objects.equal(this.series, that.series) &&
                Objects.equal(this.number, that.number) &&
                Objects.equal(this.issuingOrganization, that.issuingOrganization) &&
                Objects.equal(this.issuedDate, that.issuedDate) &&
                Objects.equal(this.citizenship, that.citizenship) &&
                Objects.equal(this.address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                actual,
                series,
                number,
                issuingOrganization,
                issuedDate,
                citizenship,
                address);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("actual", actual)
                .add("series", series)
                .add("number", number)
                .add("issuingOrganization", issuingOrganization)
                .add("issuedDate", issuedDate)
                .add("citizenship", citizenship)
                .add("address", address)
                .toString();
    }
}