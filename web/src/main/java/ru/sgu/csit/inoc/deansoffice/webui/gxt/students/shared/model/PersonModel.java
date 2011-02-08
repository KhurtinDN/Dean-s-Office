package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

import java.util.Date;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/8/11
 * Time: 11:03 AM
 */
public class PersonModel extends DtoModel {

    public Date getBirthday() {
        return get("birthday");
    }

    public void setBirthday(Date birthday) {
        set("birthday", birthday);
    }

    public String getSex() {
        return get("sex");
    }

    public void setSex(String sex) {
        set("sex", sex);
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

}
