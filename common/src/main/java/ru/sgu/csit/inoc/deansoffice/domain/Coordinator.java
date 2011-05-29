package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.Entity;

/**
 * User: MesheryakovAV
 * Date: 14.03.11
 * Time: 14:11
 */
@Entity
public class Coordinator extends PersistentItem {
    private String position;

    public Coordinator() {
    }

    public Coordinator(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Coordinator that = (Coordinator) o;

        return super.equals(that) &&
                Objects.equal(this.position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                position);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("position", position)
                .toString();
    }
}
