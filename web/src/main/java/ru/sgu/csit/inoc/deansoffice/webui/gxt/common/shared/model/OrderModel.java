package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 3/9/11
 * Time: 1:26 PM
 */
public class OrderModel extends DtoModel {

    public String getType() {
        return get("type");
    }

    public void setType(String type) {
        set("type", type);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getStatus() {
        return get("status");
    }

    public void setStatus(String status) {
        set("status", status);
    }

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final OrderModel that = (OrderModel) model;

        return super.equals(that) &&
                ObjectUtil.equal(this.getType(), that.getType()) &&
                ObjectUtil.equal(this.getName(), that.getName()) &&
                ObjectUtil.equal(this.getStatus(), that.getStatus());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getType(),
                getName(),
                getStatus());
    }
}
