package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;

/**
 * Created by XX (MesheryakovAV)
 * Date: 05.10.2010
 * Time: 10:10:09
 */
@Entity
public class Rector extends Person {
    private String degree;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
