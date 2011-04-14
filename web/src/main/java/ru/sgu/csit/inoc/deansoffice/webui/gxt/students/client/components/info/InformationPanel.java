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

        studentInfoLayoutContainer = new StudentInfoLayoutContainer();
        groupInfoLayoutContainer = new GroupInfoLayoutContainer();
        specialityInfoLayoutContainer = new SpecialityInfoLayoutContainer();
        facultyInfoLayoutContainer = new FacultyInfoLayoutContainer();

        add(studentInfoLayoutContainer);
        add(groupInfoLayoutContainer);
        add(specialityInfoLayoutContainer);
        add(facultyInfoLayoutContainer);

        studentInfoLayoutContainer.setVisible(false);
        groupInfoLayoutContainer.setVisible(false);
        specialityInfoLayoutContainer.setVisible(false);
        facultyInfoLayoutContainer.setVisible(false);
        showFacultyInformation();
    }

    public void showStudentInformation(StudentModel studentModel) {
        studentInfoLayoutContainer.bind(studentModel);

        if (currentLayoutContainer != studentInfoLayoutContainer) {
            if (currentLayoutContainer != null) {
                currentLayoutContainer.setVisible(false);
            }
            currentLayoutContainer = studentInfoLayoutContainer;
            currentLayoutContainer.setVisible(true);
        }
    }

    public void showGroupInformation(GroupModel groupModel) {
        groupInfoLayoutContainer.bind(groupModel);

        if (currentLayoutContainer != groupInfoLayoutContainer) {
            if (currentLayoutContainer != null) {
                currentLayoutContainer.setVisible(false);
            }
            currentLayoutContainer = groupInfoLayoutContainer;
            currentLayoutContainer.setVisible(true);
        }
    }

    public void showSpecialityInformation(SpecialityModel specialityModel) {
        specialityInfoLayoutContainer.bind(specialityModel);

        if (currentLayoutContainer != specialityInfoLayoutContainer) {
            if (currentLayoutContainer != null) {
                currentLayoutContainer.setVisible(false);
            }
            currentLayoutContainer = specialityInfoLayoutContainer;
            currentLayoutContainer.setVisible(true);
        }
    }

    public void showFacultyInformation() {
        if (currentLayoutContainer != facultyInfoLayoutContainer) {
            if (currentLayoutContainer != null) {
                currentLayoutContainer.setVisible(false);
            }
            currentLayoutContainer = facultyInfoLayoutContainer;
            currentLayoutContainer.setVisible(true);
        }
    }
}
