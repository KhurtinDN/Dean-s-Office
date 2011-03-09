package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model;

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
}
