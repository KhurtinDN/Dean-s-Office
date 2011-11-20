package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import java.util.Date;

/**
 * The enrollment order.
 */
@Entity
public class EnrollmentOrder extends Order {
    private Date enrollmentDate;

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(final Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
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
                Objects.equal(this.enrollmentDate, that.enrollmentDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                enrollmentDate);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("enrollmentDate", enrollmentDate)
                .toString();
    }
}
