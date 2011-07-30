package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;

/**
 * Date: 7/10/11
 * Time: 11:57 AM
 *
 * @author Denis Khurtin
 */
@Entity
public class Employee extends Person {
    private String degree;
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(final String position) {
        this.position = position;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(final String degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        final Employee that = (Employee) o;

        return super.equals(that) &&
                Objects.equal(this.degree, that.degree) &&
                Objects.equal(this.position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                degree,
                position);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("degree", degree)
                .add("position", position)
                .toString();
    }

    public static Employee make(
            final Long id,
            final String firstName,
            final String middleName,
            final String lastName,
            final String degree,
            final String position) {

        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setMiddleName(middleName);
        employee.setLastName(lastName);
        employee.setDegree(degree);
        employee.setPosition(position);

        return employee;
    }
}
