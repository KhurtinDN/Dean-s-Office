package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import java.io.Serializable;
import java.util.Date;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/8/11
 * Time: 11:03 AM
 */
@SuppressWarnings({"UnusedDeclaration"})
public class PersonModel extends DtoModel {
    private Sex sex;

    public PersonModel() {
    }

    public PersonModel(Long id) {
        super(id);
    }

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
}
