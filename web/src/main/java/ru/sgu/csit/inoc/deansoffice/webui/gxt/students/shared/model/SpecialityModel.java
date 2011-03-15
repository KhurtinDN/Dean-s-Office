package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/27/11
 * Time: 2:04 PM
 */
public class SpecialityModel extends DtoModel {

    public SpecialityModel() {
    }

    public SpecialityModel(Long id, String fullName) {
        setId(id);
        setFullName(fullName);
    }

    public String getFullName() {
        return get("fullName");
    }

    public void setFullName(String fullName) {
        set("fullName", fullName);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getCode() {
        return get("code");
    }

    public void setCode(String code) {
        set("code", code);
    }
}
