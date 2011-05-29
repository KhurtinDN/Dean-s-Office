package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import java.util.Date;

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

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnrollmentOrder that = (EnrollmentOrder) o;

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
}
