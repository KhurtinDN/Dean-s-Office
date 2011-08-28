package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import com.extjs.gxt.ui.client.data.BaseModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/7/11
 * Time: 12:28 PM
 */
public class DtoModel extends BaseModel {

    public Long getId() {
        return get("id");
    }

    public void setId(Long id) {
        set("id", id);
    }

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }

        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final DtoModel that = (DtoModel) model;

        return ObjectUtil.equal(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(getId());
    }
}
