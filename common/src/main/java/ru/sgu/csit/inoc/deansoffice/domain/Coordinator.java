package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 14.03.11
 * Time: 14:11
 */
@Entity
public class Coordinator {
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
}
