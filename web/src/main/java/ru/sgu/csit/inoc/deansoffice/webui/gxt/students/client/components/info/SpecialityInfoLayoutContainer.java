package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.info;

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
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/3/11
 * Time: 6:02 PM
 */
public class SpecialityInfoLayoutContainer extends LayoutContainer {
    private LabelField shortNameLabelField;
    private LabelField fullNameLabelField;
    private LabelField codeLabelField;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        FieldSet fieldSet = new FieldSet();
        fieldSet.setHeading("Инфомация о специальности");
        FormLayout formLayout = new FormLayout();
        formLayout.setLabelWidth(140);
        fieldSet.setLayout(formLayout);

        fullNameLabelField = new LabelField();
        fullNameLabelField.setName("fullName");
        fullNameLabelField.setFieldLabel("Полное имя:");
        fullNameLabelField.setLabelStyle("font-weight: bold");
        fullNameLabelField.setAutoWidth(true);

        shortNameLabelField = new LabelField();
        shortNameLabelField.setName("name");
        shortNameLabelField.setFieldLabel("Имя:");
        shortNameLabelField.setLabelStyle("font-weight: bold");
        shortNameLabelField.setAutoWidth(true);

        codeLabelField = new LabelField();
        codeLabelField.setName("code");
        codeLabelField.setFieldLabel("Код:");
        codeLabelField.setLabelStyle("font-weight: bold");
        codeLabelField.setAutoWidth(true);

        fieldSet.add(shortNameLabelField);
        fieldSet.add(fullNameLabelField);
        fieldSet.add(codeLabelField);

        add(fieldSet, new FlowData(5));
    }

    public void bind(SpecialityModel specialityModel) {
        if (isRendered()) {
            shortNameLabelField.setText(specialityModel.getName());
            fullNameLabelField.setText(specialityModel.getFullName());
            codeLabelField.setText(specialityModel.getCode());
        } else {
            AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.DebugInformation);
            appEvent.setData("message", "StudentInfoLayoutContainer is not rendered!");
            Dispatcher.forwardEvent(appEvent);
        }
    }
}
