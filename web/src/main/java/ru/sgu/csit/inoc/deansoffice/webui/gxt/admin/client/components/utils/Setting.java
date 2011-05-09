package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.utils;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.EventType;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/15/11
 * Time: 2:24 PM
 */
public class Setting extends BaseModelData {
    public Setting(String name, String icon) {
        setName(name);
        setIcon(icon);
    }

    public Setting(String name, String icon, EventType eventType) {
        setName(name);
        setIcon(icon);
        setEventType(eventType);
    }

    public String getName() {
        return get("name");
    }

    public void setName(String name) {
        set("name", name);
    }

    public String getIcon() {
        return get("icon");
    }

    public void setIcon(String icon) {
        set("icon", icon);
    }

    public EventType getEventType() {
        return get("eventType");
    }

    public void setEventType(EventType eventType) {
        set("eventType", eventType);
    }

    @SuppressWarnings({"unchecked"})
    public <X> X getData() {
        return (X) get("data");
    }

    public <X> void setData(X data) {
        set("data", data);
    }

    public boolean hasChildren() {
        Boolean child = get("child");
        return child != null && child;
    }

    public void setChildren(Boolean child) {
        set("child", child);
    }
}
