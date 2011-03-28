package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.references;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.FormUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.utils.ReferenceModelUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.utils.StudentModelUtil;

import java.util.Date;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/27/11
 * Time: 1:55 PM
 */
public class ReferenceInfoPanel extends FormPanel {
    private ReferenceModel referenceModel;

    private LabelField registrationDateLabelField = new LabelField();
    private LabelField nameLabelField = new LabelField();
    private LabelField groupNameLabelField = new LabelField();
    private LabelField studyFormLabelField = new LabelField();
    private LabelField typeLabelField = new LabelField();
    private TextField<String> destinationTextField = new TextField<String>();
    private LabelField statusLabelField = new LabelField();
    private LabelField issueDateLabelField = new LabelField();

    private DateTimeFormat dtf = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_MEDIUM);

    public ReferenceInfoPanel() {
        setHeading("Информация о справке");
        setLabelWidth(120);

        registrationDateLabelField.setName("registrationDate");
        registrationDateLabelField.setFieldLabel("Дата добавления");
        registrationDateLabelField.setLabelStyle("font-weight: bold");

        nameLabelField.setName("name");
        nameLabelField.setFieldLabel("ФИО");
        nameLabelField.setLabelStyle("font-weight: bold");

        groupNameLabelField.setName("groupName");
        groupNameLabelField.setFieldLabel("Группа");
        groupNameLabelField.setLabelStyle("font-weight: bold");

        studyFormLabelField.setName("studyForm");
        studyFormLabelField.setFieldLabel("Форма обучения");
        studyFormLabelField.setLabelStyle("font-weight: bold");

        typeLabelField.setName("type");
        typeLabelField.setFieldLabel("Тип");
        typeLabelField.setLabelStyle("font-weight: bold");

        destinationTextField.setName("destination");
        destinationTextField.setFieldLabel("Назначение");
        destinationTextField.setLabelStyle("font-weight: bold");

        statusLabelField.setName("status");
        statusLabelField.setFieldLabel("Статус");
        statusLabelField.setLabelStyle("font-weight: bold");

        issueDateLabelField.setName("issueDate");
        issueDateLabelField.setFieldLabel("Дата выдачи");
        issueDateLabelField.setLabelStyle("font-weight: bold");

        destinationTextField.addListener(Events.Change, new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent be) {
                if (referenceModel != null) {
                    String destination = be.getValue() != null ? be.getValue().toString() : "";
                    referenceModel.setDestination(destination);
                    Dispatcher.forwardEvent(AppEvents.UpdateReference, referenceModel);
                }
            }
        });
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        add(destinationTextField, FormUtil.wh5FormData);

        add(registrationDateLabelField, FormUtil.wh5FormData);
        add(nameLabelField, FormUtil.wh5FormData);
        add(groupNameLabelField, FormUtil.wh5FormData);
        add(studyFormLabelField, FormUtil.wh5FormData);
        add(typeLabelField, FormUtil.wh5FormData);
        add(statusLabelField, FormUtil.wh5FormData);
        add(issueDateLabelField, FormUtil.wh5FormData);
    }

    public void bind(final ReferenceModel referenceModel) {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                ReferenceInfoPanel.this.referenceModel = referenceModel;

                Date registrationDate = referenceModel.getRegistrationDate();
                registrationDateLabelField.setValue(registrationDate == null ? null : dtf.format(registrationDate));
                nameLabelField.setValue(referenceModel.getStudent().getFullName());
                groupNameLabelField.setValue(referenceModel.getStudent().getGroupName());
                studyFormLabelField.setValue(
                        StudentModelUtil.studyFormToString(referenceModel.getStudent().getStudyForm()));
                typeLabelField.setValue(ReferenceModelUtil.typeToString(referenceModel.getType()));
                destinationTextField.setValue(referenceModel.getDestination());
                statusLabelField.setValue(ReferenceModelUtil.statusToString(referenceModel.getState()));
                Date issueDate = referenceModel.getIssueDate();
                issueDateLabelField.setValue(issueDate == null ? null : dtf.format(issueDate));
            }
        });
    }

    public void unbind() {
        Scheduler.get().scheduleDeferred(new Scheduler.ScheduledCommand() {
            @Override
            public void execute() {
                ReferenceInfoPanel.this.referenceModel = null;

                registrationDateLabelField.clear();
                nameLabelField.clear();
                groupNameLabelField.clear();
                studyFormLabelField.clear();
                typeLabelField.clear();
                destinationTextField.clear();
                statusLabelField.clear();
                issueDateLabelField.clear();
            }
        });
    }
}
