package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/7/11
 * Time: 12:28 PM
 */
public class DtoModel extends BaseModel {

    public DtoModel() {
    }

    public DtoModel(Long id) {
        setId(id);
    }

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }
}
