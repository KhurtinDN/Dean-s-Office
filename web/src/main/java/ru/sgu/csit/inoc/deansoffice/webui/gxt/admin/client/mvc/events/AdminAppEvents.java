package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events;

import com.extjs.gxt.ui.client.event.EventType;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 8:23 PM
 */
public class AdminAppEvents {
    public static final EventType Init = new EventType();
    public static final EventType UIReady = new EventType();
    public static final EventType Error = new EventType();

    public static final EventType NavigationPanelReady = new EventType();

    public static final EventType UserSettingSelected = new EventType();
}
