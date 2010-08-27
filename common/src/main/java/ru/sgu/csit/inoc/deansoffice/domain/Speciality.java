package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 11:59:03 AM
 */
@Entity
public class Speciality extends PersistentItem {
    private String name;
    private String code;
    private Faculty faculty;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
