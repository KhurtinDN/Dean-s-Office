package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/27/11
 * Time: 2:05 PM
 */
public class GroupModel extends DtoModel {
    public GroupModel() {
    }

    public GroupModel(Long id, String name) {
        setId(id);
        setName(name);
    }

    public SpecialityModel getSpeciality() {
        return get("speciality");
    }

    public void setSpeciality(SpecialityModel specialityModel) {
        set("speciality", specialityModel);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }
}
