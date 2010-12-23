package ru.sgu.csit.inoc.deansoffice.domain;

import javax.persistence.MappedSuperclass;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 11:30:09 AM
 */
@MappedSuperclass
public abstract class Person extends PersistentItem {
    protected Sex sex;

    protected String firstName;
    protected String middleName;
    protected String lastName;

    protected String firstNameDative;
    protected String middleNameDative;
    protected String lastNameDative;

    public String generateShortName(boolean directOrder) {
        String shortName = lastName;
        String initials = firstName.substring(0,1) + "." + middleName.substring(0,1) + ".";

        if (directOrder) {
            shortName = initials + " " + shortName;
        } else {
            shortName = shortName + " " + initials;
        }
        return shortName;
    }

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

    public String getFirstNameDative() {
        return firstNameDative;
    }

    public void setFirstNameDative(String firstNameDative) {
        this.firstNameDative = firstNameDative;
    }

    public String getMiddleNameDative() {
        return middleNameDative;
    }

    public void setMiddleNameDative(String middleNameDative) {
        this.middleNameDative = middleNameDative;
    }

    public String getLastNameDative() {
        return lastNameDative;
    }

    public void setLastNameDative(String lastNameDative) {
        this.lastNameDative = lastNameDative;
    }

    public enum Sex {
        MALE, FEMALE
    }
}
