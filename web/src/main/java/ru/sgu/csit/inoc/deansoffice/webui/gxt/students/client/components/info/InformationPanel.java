package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.info;

import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/3/11
 * Time: 11:01 AM
 */
public class InformationPanel extends ContentPanel {
    private LayoutContainer currentLayoutContainer;
    private StudentInfoLayoutContainer studentInfoLayoutContainer;
    private GroupInfoLayoutContainer groupInfoLayoutContainer;
    private SpecialityInfoLayoutContainer specialityInfoLayoutContainer;
    private FacultyInfoLayoutContainer facultyInfoLayoutContainer;

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setHeading("Свойства");
        setLayout(new FitLayout());

        showFacultyInformation();
    }

    public void showStudentInformation(StudentModel studentModel) {
        if (studentInfoLayoutContainer == null) {
            studentInfoLayoutContainer = new StudentInfoLayoutContainer();
        }

        showInformation(studentInfoLayoutContainer);

        studentInfoLayoutContainer.bind(studentModel);
    }

    public void showGroupInformation(GroupModel groupModel) {
        if (groupInfoLayoutContainer == null) {
            groupInfoLayoutContainer = new GroupInfoLayoutContainer();
        }

        showInformation(groupInfoLayoutContainer);

        groupInfoLayoutContainer.bind(groupModel);
    }

    public void showSpecialityInformation(SpecialityModel specialityModel) {
        if (specialityInfoLayoutContainer == null) {
            specialityInfoLayoutContainer = new SpecialityInfoLayoutContainer();
        }

        showInformation(specialityInfoLayoutContainer);

        specialityInfoLayoutContainer.bind(specialityModel);
    }

    public void showFacultyInformation() {
        if (specialityInfoLayoutContainer == null) {
            facultyInfoLayoutContainer = new FacultyInfoLayoutContainer();
        }

        showInformation(facultyInfoLayoutContainer);
    }

    private void showInformation(final LayoutContainer layoutContainer) {
        if (currentLayoutContainer != layoutContainer) {
            if (currentLayoutContainer != null) {
                remove(currentLayoutContainer);
            }
            currentLayoutContainer = layoutContainer;
            add(currentLayoutContainer);
            layout(true);
        }
    }
}
