package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.*;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 11:58:49 AM
 */
@Entity
@Table(name = "`Group`")
public class Group extends PersistentItem {
    private String name;
    private Integer course;

    @ManyToOne(cascade = CascadeType.MERGE)
    @PrimaryKeyJoinColumn
    private Speciality speciality;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getCourse() {
        return course;
    }

    public void setCourse(final Integer course) {
        this.course = course;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(final Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Group that = (Group) o;

        return super.equals(that) &&
                Objects.equal(this.name, that.name) &&
                Objects.equal(this.course, that.course) &&
                Objects.equal(this.speciality, that.speciality);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                name,
                course,
                speciality);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("name", name)
                .add("course", course)
                .add("speciality", speciality)
                .toString();
    }

    public static Group make(
            final Long id,
            final String name,
            final Integer course,
            final Speciality speciality) {

        final Group group = new Group();
        group.setId(id);
        group.setName(name);
        group.setCourse(course);
        group.setSpeciality(speciality);

        return group;
    }
}
