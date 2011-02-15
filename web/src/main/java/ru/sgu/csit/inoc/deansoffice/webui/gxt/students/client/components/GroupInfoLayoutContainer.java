package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.constants.ErrorCode;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/3/11
 * Time: 6:02 PM
 */
public class GroupInfoLayoutContainer extends LayoutContainer {

    private LabelField nameLabelField;
    private LabelField specialityLabelField;
    private LabelField courseLabelField;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        FieldSet fieldSet = new FieldSet();
        fieldSet.setHeading("Инфомация о группе");
        FormLayout formLayout = new FormLayout();
        formLayout.setLabelWidth(140);
        fieldSet.setLayout(formLayout);

        nameLabelField = new LabelField();
        nameLabelField.setName("fullName");
        nameLabelField.setFieldLabel("Полное имя:");
        nameLabelField.setLabelStyle("font-weight: bold");
        nameLabelField.setAutoWidth(true);

        specialityLabelField = new LabelField();
        specialityLabelField.setName("speciality");
        specialityLabelField.setFieldLabel("Специальность:");
        specialityLabelField.setLabelStyle("font-weight: bold");
        specialityLabelField.setAutoWidth(true);

        courseLabelField = new LabelField();
        courseLabelField.setName("course");
        courseLabelField.setFieldLabel("Курс:");
        courseLabelField.setLabelStyle("font-weight: bold");
        courseLabelField.setAutoWidth(true);

        fieldSet.add(nameLabelField);
        fieldSet.add(specialityLabelField);
        fieldSet.add(courseLabelField);

        add(fieldSet, new FlowData(5));
    }

    public void bind(GroupModel groupModel) {
        if (isRendered()) {
            nameLabelField.setText(groupModel.getName());
            specialityLabelField.setText(groupModel.getSpeciality().getFullName());
            courseLabelField.setText(groupModel.getCourse().toString());
        } else {
            AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.DebugInformation);
            appEvent.setData("message", "GroupInfoLayoutContainer is not rendered!");
            Dispatcher.forwardEvent(appEvent);
        }
    }
}
