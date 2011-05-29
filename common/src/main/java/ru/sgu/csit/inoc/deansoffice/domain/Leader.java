package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;

/**
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 12:18
 */
@Entity
public class Leader extends Person {
    private String degree;
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Leader that = (Leader) o;

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
}
