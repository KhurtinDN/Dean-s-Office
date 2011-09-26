package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.info;

import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.Image;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;

/**
 * @author Denis Khurtin
 */
public class GroupInfoLayoutContainer extends LayoutContainer {

    private final LabelField nameLabelField = new LabelField();
    private final LabelField specialityLabelField = new LabelField();
    private final LabelField courseLabelField = new LabelField();

    public GroupInfoLayoutContainer() {
        nameLabelField.setName("name");
        nameLabelField.setFieldLabel("Имя:");
        nameLabelField.setLabelStyle("font-weight: bold");
        nameLabelField.setAutoWidth(true);

        specialityLabelField.setName("specialityName");
        specialityLabelField.setFieldLabel("Специальность:");
        specialityLabelField.setLabelStyle("font-weight: bold");
        specialityLabelField.setAutoWidth(true);

        courseLabelField.setName("course");
        courseLabelField.setFieldLabel("Курс:");
        courseLabelField.setLabelStyle("font-weight: bold");
        courseLabelField.setAutoWidth(true);
    }

    @Override
    protected void onRender(final Element parent, final int index) {
        super.onRender(parent, index);

        final FieldSet fieldSet = new FieldSet();
        fieldSet.setHeading("Информация о группе");
        final FormLayout formLayout = new FormLayout();
        formLayout.setLabelWidth(140);
        fieldSet.setLayout(formLayout);

        final Image image = new Image("icons/group128x128.png");
        image.setHeight("100px");

        fieldSet.add(image);
        fieldSet.add(nameLabelField);
        fieldSet.add(specialityLabelField);
        fieldSet.add(courseLabelField);

        add(fieldSet, new FlowData(5));
    }

    public void bind(final GroupModel groupModel) {
        nameLabelField.setText(groupModel.getName());
        specialityLabelField.setText(groupModel.getSpeciality().getName());
        courseLabelField.setText(groupModel.getCourse().toString());
    }
}
