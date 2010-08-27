package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.Entity;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 11:59:29 AM
 */
@Entity
public class Faculty extends PersistentItem {
    private String name;
    private Dean dean;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dean getDean() {
        return dean;
    }

    public void setDean(Dean dean) {
        this.dean = dean;
    }
}
