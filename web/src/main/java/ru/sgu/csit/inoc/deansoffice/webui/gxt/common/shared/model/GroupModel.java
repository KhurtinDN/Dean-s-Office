package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/27/11
 * Time: 2:05 PM
 */
@SuppressWarnings({"UnusedDeclaration"})
public class GroupModel extends DtoModel {
    private SpecialityModel speciality;

    public GroupModel() {
    }

    public GroupModel(Long id, String name) {
        setId(id);
        setName(name);
    }

    public Integer getCourse() {
        return get("course");
    }

    public void setCourse(Integer course) {
        set("course", course);
    }

    public SpecialityModel getSpeciality() {
        return get("speciality");
    }

    public void setSpeciality(SpecialityModel speciality) {
        set("speciality", speciality);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }
}
