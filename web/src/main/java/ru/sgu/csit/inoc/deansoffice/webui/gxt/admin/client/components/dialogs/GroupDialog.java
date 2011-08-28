package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.dialogs;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.KeyNav;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.GroupService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.SpecialityService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class GroupDialog extends Window {
    private FormPanel formPanel = new FormPanel();

    private final TextField<String> nameTextField = new TextField<String>();
    private final NumberField courseNumberField = new NumberField();
    private final ComboBox<SpecialityModel> specialityComboBox = new ComboBox<SpecialityModel>();

    private GroupModel currentGroupModel;

    private Long facultyId;

    public GroupDialog(final SpecialityModel specialityModel) {
        if (specialityModel != null && specialityModel.getFaculty() != null) {
            facultyId = specialityModel.getFaculty().getId();
        }

        setHeading(MESSAGES.addGroupDialogHeading());
        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        setModal(true);
        setSize(400, 180);

        formPanel.setHeaderVisible(false);

        nameTextField.setFieldLabel(MESSAGES.name());
        nameTextField.setAutoWidth(true);
        nameTextField.setAllowBlank(false);

        courseNumberField.setFieldLabel(MESSAGES.course());
        courseNumberField.setPropertyEditorType(Integer.class);
        courseNumberField.setAutoWidth(true);
        courseNumberField.setAllowBlank(false);

        specialityComboBox.setFieldLabel(MESSAGES.faculty());
        specialityComboBox.setAutoWidth(true);
        specialityComboBox.setAllowBlank(false);
        specialityComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        specialityComboBox.setDisplayField("fullName");
        specialityComboBox.setValue(specialityModel);
    }

    public GroupDialog(final GroupModel groupModel) {
        this(groupModel.getSpeciality());
        this.currentGroupModel = groupModel;

        setHeading(MESSAGES.editSpecialityDialogHeading());

        nameTextField.setValue(groupModel.getName());
        courseNumberField.setValue(groupModel.getCourse());
        specialityComboBox.setValue(groupModel.getSpeciality());
    }

    @Override
    protected void onRender(final Element parent, final int pos) {
        super.onRender(parent, pos);

        final RpcProxy<List<SpecialityModel>> proxy = new RpcProxy<List<SpecialityModel>>() {
            @Override
            protected void load(final Object loadConfig, final AsyncCallback<List<SpecialityModel>> listAsyncCallback) {
                if (facultyId != null) {
                    SpecialityService.Util.getInstance().loadSpecialities(facultyId, listAsyncCallback);
                }
            }
        };

        final ListLoader<ListLoadResult<SpecialityModel>> loader = new BaseListLoader<ListLoadResult<SpecialityModel>>(proxy);
        specialityComboBox.setStore(new ListStore<SpecialityModel>(loader));

        new KeyNav<ComponentEvent>(formPanel) {
            @Override
            public void onEnter(final ComponentEvent ce) {
                saveOrUpdate();
            }
        };

        final Button saveButton = new Button(MESSAGES.save(), IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                saveOrUpdate();
            }
        });

        final Button cancelButton = new Button(MESSAGES.cancel(), IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                hide();
            }
        });

        formPanel.add(nameTextField, FormUtil.w5FormData);
        formPanel.add(courseNumberField, FormUtil.w5FormData);
        formPanel.add(specialityComboBox, FormUtil.w5FormData);

        formPanel.addButton(saveButton);
        formPanel.addButton(cancelButton);

        add(formPanel, new RowData(1, 1));
    }

    @Override
    public void show() {
        super.show();
        nameTextField.focus();
    }

    private void saveOrUpdate() {
        if (formPanel.isValid()) {
            final boolean adding = (currentGroupModel == null);

            if (currentGroupModel == null) {
                currentGroupModel = new GroupModel();
            }

            currentGroupModel.setName(nameTextField.getValue());
            currentGroupModel.setCourse(courseNumberField.getValue().intValue());
            currentGroupModel.setSpeciality(specialityComboBox.getValue());

            GroupService.Util.getInstance().saveOrUpdate(
                    currentGroupModel,
                    new BaseAsyncCallback<GroupModel>() {
                        @Override
                        public void onSuccess(final GroupModel groupModel) {
                            if (adding) {
                                Dispatcher.forwardEvent(AdminEvents.GroupAdded, groupModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.addGroupSuccessMessage());
                            } else {
                                Dispatcher.forwardEvent(AdminEvents.GroupAdded, groupModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.editGroupSuccessMessage());
                            }
                        }
                    });

            hide();
        }
    }
}
