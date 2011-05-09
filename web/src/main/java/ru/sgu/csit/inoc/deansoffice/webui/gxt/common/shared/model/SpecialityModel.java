package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/27/11
 * Time: 2:04 PM
 */
@SuppressWarnings({"UnusedDeclaration"})
public class SpecialityModel extends DtoModel {
    private FacultyModel faculty;

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

    public FacultyModel getFaculty() {
        return get("faculty");
    }

    public void setFaculty(FacultyModel faculty) {
        set("faculty", faculty);
    }
}
