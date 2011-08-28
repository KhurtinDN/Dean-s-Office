package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Denis Khurtin
 */
public class PersonModel extends DtoModel {
    private Sex marker;

    public String getFullName() {
        return get("fullName");
    }

    public void setFullName(String fullName) {
        set("fullName", fullName);
    }

    public String getFirstName() {
        return get("firstName");
    }

    public void setFirstName(String firstName) {
        set("firstName", firstName);
    }

    public String getMiddleName() {
        return get("middleName");
    }

    public void setMiddleName(String middleName) {
        set("middleName", middleName);
    }

    public String getLastName() {
        return get("lastName");
    }

    public void setLastName(String lastName) {
        set("lastName", lastName);
    }

    public String getFirstNameGenitive() {
        return get("firstNameGenitive");
    }

    public void setFirstNameGenitive(String firstNameGenitive) {
        set("firstNameGenitive", firstNameGenitive);
    }

    public String getMiddleNameGenitive() {
        return get("middleNameGenitive");
    }

    public void setMiddleNameGenitive(String middleNameGenitive) {
        set("middleNameGenitive", middleNameGenitive);
    }

    public String getLastNameGenitive() {
        return get("lastNameGenitive");
    }

    public void setLastNameGenitive(String lastNameGenitive) {
        set("lastNameGenitive", lastNameGenitive);
    }

    public String getFirstNameDative() {
        return get("firstNameDative");
    }

    public void setFirstNameDative(String firstNameDative) {
        set("firstNameDative", firstNameDative);
    }

    public String getMiddleNameDative() {
        return get("middleNameDative");
    }

    public void setMiddleNameDative(String middleNameDative) {
        set("middleNameDative", middleNameDative);
    }

    public String getLastNameDative() {
        return get("lastNameDative");
    }

    public void setLastNameDative(String lastNameDative) {
        set("lastNameDative", lastNameDative);
    }

    public Date getBirthday() {
        return get("birthday");
    }

    public void setBirthday(Date birthday) {
        set("birthday", birthday);
    }

    public String getAddress() {
        return get("address");
    }

    public void setAddress(String address) {
        set("address", address);
    }

    public Sex getSex() {
        return get("sex");
    }

    public void setSex(Sex sex) {
        set("sex", sex);
    }

    public enum Sex implements Serializable { MALE, FEMALE }

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final PersonModel that = (PersonModel) model;

        return super.equals(that) &&
                ObjectUtil.equal(this.getFullName(), that.getFullName()) &&
                ObjectUtil.equal(this.getLastName(), that.getLastName()) &&
                ObjectUtil.equal(this.getFirstName(), that.getFirstName()) &&
                ObjectUtil.equal(this.getMiddleName(), that.getMiddleName()) &&
                ObjectUtil.equal(this.getLastNameGenitive(), that.getLastNameGenitive()) &&
                ObjectUtil.equal(this.getFirstNameGenitive(), that.getLastNameGenitive()) &&
                ObjectUtil.equal(this.getMiddleNameGenitive(), that.getMiddleNameGenitive()) &&
                ObjectUtil.equal(this.getLastNameDative(), that.getLastNameDative()) &&
                ObjectUtil.equal(this.getFirstNameDative(), that.getFirstNameDative()) &&
                ObjectUtil.equal(this.getMiddleNameDative(), that.getMiddleNameDative()) &&
                ObjectUtil.equal(this.getBirthday(), that.getBirthday()) &&
                ObjectUtil.equal(this.getAddress(), that.getAddress()) &&
                ObjectUtil.equal(this.getSex(), that.getSex());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getFullName(),
                getLastName(),
                getFirstName(),
                getMiddleName(),
                getLastNameGenitive(),
                getFirstNameGenitive(),
                getMiddleNameGenitive(),
                getLastNameDative(),
                getFirstNameDative(),
                getMiddleNameDative(),
                getBirthday(),
                getAddress(),
                getSex());
    }
}
