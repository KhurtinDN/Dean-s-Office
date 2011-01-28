package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/27/11
 * Time: 2:04 PM
 */
public class SpecialityModel extends BaseModel {
    public SpecialityModel() {
    }

    public SpecialityModel(Long id, String name) {
        setId(id);
        setName(name);
    }

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }
}
