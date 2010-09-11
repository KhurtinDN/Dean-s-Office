package ru.sgu.csit.inoc.deansoffice.domain;

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
}
