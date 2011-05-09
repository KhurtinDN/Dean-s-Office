package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events;

import com.extjs.gxt.ui.client.event.EventType;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 2:20 PM
 */
public class StudentEvents {
    public static final EventType UIReady = new EventType();
    public static final EventType MenuBarReady = new EventType();
    public static final EventType StatusBarReady = new EventType();

    public static final EventType NavigationPanelReady = new EventType();

    public static final EventType StudentsPanelReady = new EventType();
    public static final EventType InformationPanelReady = new EventType();

    public static final EventType FacultySelected = new EventType();
    public static final EventType SpecialitySelected = new EventType();
    public static final EventType GroupSelected = new EventType();
    public static final EventType StudentSelected = new EventType();

    public static final EventType AddReference = new EventType();
    public static final EventType RemoveReference = new EventType();
    public static final EventType UpdateReference = new EventType();
    public static final EventType RegistrationReference = new EventType();
    public static final EventType PrintReference = new EventType();
    public static final EventType ReadyReference = new EventType();
    public static final EventType IssueReference = new EventType();
    public static final EventType ReferenceQueueCall = new EventType();

    public static final EventType OrderQueueCall = new EventType();
    public static final EventType AddNewOrderCall = new EventType();
    public static final EventType EditOrderCall = new EventType();
}
