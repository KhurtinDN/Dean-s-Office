package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events;

import com.extjs.gxt.ui.client.event.EventType;

/**
 * @author Denis Khurtin
 */
public class AdminEvents {
    public static final EventType UIReady = new EventType();
    public static final EventType MenuBarReady = new EventType();
    public static final EventType StatusBarReady = new EventType();

    public static final EventType NavigationPanelReady = new EventType();

    public static final EventType InstituteSettingSelected = new EventType();
    public static final EventType UsersSettingSelected = new EventType();

    public static final EventType FacultiesSettingSelected = new EventType();
    public static final EventType FacultySettingSelected = new EventType();
    public static final EventType SpecialitySettingSelected = new EventType();
    public static final EventType GroupSettingSelected = new EventType();

    public static final EventType InstitutionChanged = new EventType();

    public static final EventType FacultyAdded = new EventType();
    public static final EventType FacultyChanged = new EventType();
    public static final EventType FacultiesDeleted = new EventType();

    public static final EventType SpecialityAdded = new EventType();
    public static final EventType SpecialityChanged = new EventType();
    public static final EventType SpecialitiesDeleted = new EventType();

    public static final EventType GroupAdded = new EventType();
    public static final EventType GroupChanged = new EventType();
    public static final EventType GroupsDeleted = new EventType();

    public static final EventType StudentAdded = new EventType();
    public static final EventType StudentChanged = new EventType();
    public static final EventType StudentsDeleted = new EventType();

    public static final EventType UserAdded = new EventType();
    public static final EventType UserChanged = new EventType();
    public static final EventType UsersDeleted = new EventType();

    public static final EventType EmployeeAdded = new EventType();
    public static final EventType EmployeeChanged = new EventType();
    public static final EventType EmployeesDeleted = new EventType();

    public static final EventType ShowSettingsPanel = new EventType();
}
