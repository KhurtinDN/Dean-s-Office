package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 23.12.10
 * Time: 14:28
 */
@Entity
public class Parent extends Person {
    private Date birthday;
    private Address adress;
}
