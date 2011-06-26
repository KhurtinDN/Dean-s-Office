package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import java.util.Date;
import java.util.List;

/**
 * Created by XX (MesheryakovAV)
 * Date: 10.09.2010
 * Time: 8:38:57
 */
@Entity
public class EnrollmentOrder extends Order {
    private Date enrollmentDate;
    private Date releaseDate;

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(final Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final EnrollmentOrder that = (EnrollmentOrder) o;

        return super.equals(that) &&
                Objects.equal(this.enrollmentDate, that.enrollmentDate) &&
                Objects.equal(this.releaseDate, that.releaseDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                enrollmentDate,
                releaseDate);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("enrollmentDate", enrollmentDate)
                .add("releaseDate", releaseDate)
                .toString();
    }

    public static EnrollmentOrder make(
            final Long id,
            final String number,
            final Date signedDate,
            final Template template,
            final OrderState state,
            final List<Directive> directives,
            final OrderData data,
            final Date enrollmentDate,
            final Date releaseDate) {

        final EnrollmentOrder enrollmentOrder = new EnrollmentOrder();
        enrollmentOrder.setId(id);
        enrollmentOrder.setNumber(number);
        enrollmentOrder.setSignedDate(signedDate);
        enrollmentOrder.setPrintTemplate(template);
        enrollmentOrder.setState(state);
        enrollmentOrder.setDirectives(directives);
        enrollmentOrder.setData(data);
        enrollmentOrder.setEnrollmentDate(enrollmentDate);
        enrollmentOrder.setReleaseDate(releaseDate);

        return enrollmentOrder;
    }
}
