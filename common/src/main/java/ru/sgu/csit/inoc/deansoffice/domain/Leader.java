package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
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
}
