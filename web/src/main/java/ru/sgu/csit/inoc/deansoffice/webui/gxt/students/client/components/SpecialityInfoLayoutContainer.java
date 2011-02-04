package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/3/11
 * Time: 6:02 PM
 */
public class SpecialityInfoLayoutContainer extends LayoutContainer {
    private LabelField nameLabelField;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        FieldSet fieldSet = new FieldSet();
        fieldSet.setHeading("Инфомация о специальности");
        FormLayout formLayout = new FormLayout();
        formLayout.setLabelWidth(140);
        fieldSet.setLayout(formLayout);

        nameLabelField = new LabelField();
        nameLabelField.setName("name");
        nameLabelField.setFieldLabel("Имя:");
        nameLabelField.setLabelStyle("font-weight: bold");
        nameLabelField.setAutoWidth(true);

        fieldSet.add(nameLabelField);

        add(fieldSet, new FlowData(5));
    }

    public void bind(SpecialityModel specialityModel) {
        if (isRendered()) {
            nameLabelField.setText(specialityModel.getName());
        } else {
            Info.display("DEBUG", "GroupInfoLayoutContainer is not rendered!");
        }
    }
}
