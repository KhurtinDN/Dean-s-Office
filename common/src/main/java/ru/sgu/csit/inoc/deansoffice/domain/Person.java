package ru.sgu.csit.inoc.deansoffice.domain;

import com.google.common.base.Objects;

import javax.persistence.MappedSuperclass;
import java.util.Date;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Aug 27, 2010
 * Time: 11:30:09 AM
 */
@MappedSuperclass
public abstract class Person extends PersistentItem {
    protected Date birthday;
    protected Sex sex;

    protected String firstName;
    protected String middleName;
    protected String lastName;

    protected String firstNameGenitive;
    protected String middleNameGenitive;
    protected String lastNameGenitive;

    protected String firstNameDative;
    protected String middleNameDative;
    protected String lastNameDative;

    public String generateShortName(final boolean directOrder) {
        String shortName = lastName;
        String initials = firstName.substring(0,1) + "." + middleName.substring(0,1) + ".";

        if (directOrder) {
            shortName = initials + " " + shortName;
        } else {
            shortName = shortName + " " + initials;
        }
        return shortName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(final Date birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(final String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public String getFirstNameGenitive() {
        return firstNameGenitive;
    }

    public void setFirstNameGenitive(final String firstNameGenitive) {
        this.firstNameGenitive = firstNameGenitive;
    }

    public String getMiddleNameGenitive() {
        return middleNameGenitive;
    }

    public void setMiddleNameGenitive(final String middleNameGenitive) {
        this.middleNameGenitive = middleNameGenitive;
    }

    public String getLastNameGenitive() {
        return lastNameGenitive;
    }

    public void setLastNameGenitive(final String lastNameGenitive) {
        this.lastNameGenitive = lastNameGenitive;
    }

    public String getFirstNameDative() {
        return firstNameDative;
    }

    public void setFirstNameDative(final String firstNameDative) {
        this.firstNameDative = firstNameDative;
    }

    public String getMiddleNameDative() {
        return middleNameDative;
    }

    public void setMiddleNameDative(final String middleNameDative) {
        this.middleNameDative = middleNameDative;
    }

    public String getLastNameDative() {
        return lastNameDative;
    }

    public void setLastNameDative(final String lastNameDative) {
        this.lastNameDative = lastNameDative;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(final Sex sex) {
        this.sex = sex;
    }

    public enum Sex {
        MALE, FEMALE, UNKNOWN
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Person)) {
            return false;
        }

        final Person that = (Person) o;

        return super.equals(that) &&
                Objects.equal(this.birthday, that.birthday) &&
                Objects.equal(this.sex, that.sex) &&
                Objects.equal(this.firstName, that.firstName) &&
                Objects.equal(this.middleName, that.middleName) &&
                Objects.equal(this.lastName, that.lastName) &&
                Objects.equal(this.firstNameGenitive, that.firstNameGenitive) &&
                Objects.equal(this.middleNameGenitive, that.middleNameGenitive) &&
                Objects.equal(this.lastNameGenitive, that.lastNameGenitive) &&
                Objects.equal(this.firstNameDative, that.firstNameDative) &&
                Objects.equal(this.middleNameDative, that.middleNameDative) &&
                Objects.equal(this.lastNameDative, that.lastNameDative);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(
                super.hashCode(),
                birthday,
                sex,
                firstName,
                middleName,
                lastName,
                firstNameGenitive,
                middleNameGenitive,
                lastNameGenitive,
                firstNameDative,
                middleNameDative,
                lastNameDative);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .addValue(super.toString())
                .add("birthday", birthday)
                .add("sex", sex)
                .add("firstName", firstName)
                .add("middleName", middleName)
                .add("lastName", lastName)
                .add("firstNameGenitive", firstNameGenitive)
                .add("middleNameGenitive", middleNameGenitive)
                .add("lastNameGenitive", lastNameGenitive)
                .add("firstNameDative", firstNameDative)
                .add("middleNameDative", middleNameDative)
                .add("lastNameDative", lastNameDative)
                .toString();
    }
}
