package ru.sgu.csit.inoc.deansoffice.office.shared.dto;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 28, 2010
 * Time: 1:18:44 PM
 */
public class SpecialityDto extends BaseModel {
    public SpecialityDto() {
    }

    public SpecialityDto(Long id, String name) {
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
