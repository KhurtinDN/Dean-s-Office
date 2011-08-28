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
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.SpecialityService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;

import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class SpecialityDialog extends Window {
    private FormPanel formPanel = new FormPanel();

    private final TextField<String> fullNameTextField = new TextField<String>();
    private final TextField<String> nameTextField = new TextField<String>();
    private final TextField<String> codeTextField = new TextField<String>();
    private final ComboBox<FacultyModel> facultyComboBox = new ComboBox<FacultyModel>();
    private final NumberField courseCountNumberField = new NumberField();

    private SpecialityModel currentSpecialityModel;

    public SpecialityDialog(final FacultyModel facultyModel) {
        setHeading(MESSAGES.addSpecialityDialogHeading());
        setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        setModal(true);
        setSize(400, 230);

        formPanel.setHeaderVisible(false);
        formPanel.setLabelWidth(120);

        fullNameTextField.setFieldLabel(MESSAGES.fullName());
        fullNameTextField.setAutoWidth(true);
        fullNameTextField.setAllowBlank(false);

        nameTextField.setFieldLabel(MESSAGES.name());
        nameTextField.setAutoWidth(true);
        nameTextField.setAllowBlank(false);

        codeTextField.setFieldLabel(MESSAGES.code());
        codeTextField.setAutoWidth(true);
        codeTextField.setAllowBlank(false);

        facultyComboBox.setFieldLabel(MESSAGES.faculty());
        facultyComboBox.setAutoWidth(true);
        facultyComboBox.setAllowBlank(false);
        facultyComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        facultyComboBox.setDisplayField("fullName");
        facultyComboBox.setValue(facultyModel);

        courseCountNumberField.setFieldLabel(MESSAGES.courseCount());
        courseCountNumberField.setPropertyEditorType(Integer.class);
        courseCountNumberField.setAutoWidth(true);
        courseCountNumberField.setAllowBlank(false);
    }

    public SpecialityDialog(final SpecialityModel specialityModel) {
        this(specialityModel.getFaculty());
        this.currentSpecialityModel = specialityModel;

        setHeading(MESSAGES.editSpecialityDialogHeading());

        fullNameTextField.setValue(specialityModel.getFullName());
        nameTextField.setValue(specialityModel.getName());
        codeTextField.setValue(specialityModel.getCode());
        facultyComboBox.setValue(specialityModel.getFaculty());
        courseCountNumberField.setValue(specialityModel.getCourseCount());
    }

    @Override
    protected void onRender(final Element parent, final int pos) {
        super.onRender(parent, pos);

        final RpcProxy<List<FacultyModel>> proxy = new RpcProxy<List<FacultyModel>>() {
            @Override
            protected void load(final Object loadConfig, final AsyncCallback<List<FacultyModel>> listAsyncCallback) {
                FacultyService.Util.getInstance().loadFaculties(listAsyncCallback);
            }
        };

        final ListLoader<ListLoadResult<FacultyModel>> loader = new BaseListLoader<ListLoadResult<FacultyModel>>(proxy);
        facultyComboBox.setStore(new ListStore<FacultyModel>(loader));

        new KeyNav<ComponentEvent>(formPanel) {
            @Override
            public void onEnter(final ComponentEvent ce) {
                saveOrUpdate();
            }
        };

        final Button saveButton = new Button(MESSAGES.save(), IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                saveOrUpdate();
            }
        });

        final Button cancelButton = new Button(MESSAGES.cancel(), IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                hide();
            }
        });

        formPanel.add(fullNameTextField, FormUtil.w5FormData);
        formPanel.add(nameTextField, FormUtil.w5FormData);
        formPanel.add(codeTextField, FormUtil.w5FormData);
        formPanel.add(facultyComboBox, FormUtil.w5FormData);
        formPanel.add(courseCountNumberField, FormUtil.w5FormData);

        formPanel.addButton(saveButton);
        formPanel.addButton(cancelButton);

        add(formPanel, new RowData(1, 1));
    }

    @Override
    public void show() {
        super.show();
        fullNameTextField.focus();
    }

    private void saveOrUpdate() {
        if (formPanel.isValid()) {
            final boolean adding = (currentSpecialityModel == null);

            if (currentSpecialityModel == null) {
                currentSpecialityModel = new SpecialityModel();
            }

            currentSpecialityModel.setFullName(fullNameTextField.getValue());
            currentSpecialityModel.setName(nameTextField.getValue());
            currentSpecialityModel.setCode(codeTextField.getValue());
            currentSpecialityModel.setFaculty(facultyComboBox.getValue());
            currentSpecialityModel.setCourseCount(courseCountNumberField.getValue().intValue());

            SpecialityService.Util.getInstance().saveOrUpdate(
                    currentSpecialityModel,
                    new BaseAsyncCallback<SpecialityModel>() {
                        @Override
                        public void onSuccess(final SpecialityModel specialityModel) {
                            if (adding) {
                                Dispatcher.forwardEvent(AdminEvents.SpecialityAdded, specialityModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.addSpecialitySuccessMessage());
                            } else {
                                Dispatcher.forwardEvent(AdminEvents.SpecialityChanged, specialityModel);
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.editSpecialitySuccessMessage());
                            }
                        }
                    });

            hide();
        }
    }
}
