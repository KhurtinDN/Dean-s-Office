package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 11:59:03 AM
 */
@Entity
public class Speciality extends PersistentItem {
    private String name;
    private String shortName;
    private String code;
    private Integer courseCount;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Faculty faculty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Integer getCourseCount() {
        return courseCount;
    }

    public void setCourseCount(Integer courseCount) {
        this.courseCount = courseCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Speciality that = (Speciality) o;

        return super.equals(that) &&
                Objects.equal(this.name, that.name) &&
                Objects.equal(this.shortName, that.shortName) &&
                Objects.equal(this.code, that.code) &&
                Objects.equal(this.courseCount, that.courseCount) &&
                Objects.equal(this.faculty, that.faculty);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                name,
                shortName,
                code,
                courseCount,
                faculty);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("name", name)
                .add("shortName", shortName)
                .add("code", code)
                .add("courseCount", courseCount)
                .add("faculty", faculty)
                .toString();
    }
}
