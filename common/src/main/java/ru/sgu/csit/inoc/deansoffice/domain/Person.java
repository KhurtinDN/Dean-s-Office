package ru.sgu.csit.inoc.deansoffice.domain;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 11:30:09 AM
 */
public abstract class Person extends PersistentItem {
    protected String firstName;
    protected String middleName;
    protected String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
