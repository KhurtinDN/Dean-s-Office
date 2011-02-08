package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/27/11
 * Time: 2:04 PM
 */
public class SpecialityModel extends DtoModel {
    public SpecialityModel() {
    }

    public SpecialityModel(Long id, String name) {
        setId(id);
        setName(name);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }
}
