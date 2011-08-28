package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.dialogs.SpecialityDialog;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.SpecialitiesGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.EmployeeService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.SpecialityService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.FormUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.Validate;

import java.util.ArrayList;
import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class FacultyPanel extends ContentPanel {
    private final TextField<String> fullNameTextField = new TextField<String>();
    private final TextField<String> nameTextField = new TextField<String>();
    private final ComboBox<EmployeeModel> deansComboBox = new ComboBox<EmployeeModel>();

    private final SpecialitiesGrid specialitiesGrid = new SpecialitiesGrid();

    private FacultyModel currentFacultyModel;

    public FacultyPanel() {
        setHeading(MESSAGES.facultyPanelHeading());
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        fullNameTextField.setFieldLabel(MESSAGES.fullName());
        fullNameTextField.setAllowBlank(false);

        nameTextField.setFieldLabel(MESSAGES.name());
        nameTextField.setAllowBlank(false);

        deansComboBox.setFieldLabel(MESSAGES.dean());
        deansComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        deansComboBox.setDisplayField("fullName");

        final RpcProxy<List<EmployeeModel>> proxy = new RpcProxy<List<EmployeeModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<EmployeeModel>> listAsyncCallback) {
                EmployeeService.Util.getInstance().loadDeanList(listAsyncCallback);
            }
        };

        final ListLoader<ListLoadResult<EmployeeModel>> loader = new BaseListLoader<ListLoadResult<EmployeeModel>>(proxy);
        deansComboBox.setStore(new ListStore<EmployeeModel>(loader));
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        final Button addSpecialityButton = new Button(MESSAGES.add(), IconHelper.createStyle("addButton-icon"));
        addSpecialityButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final SpecialityDialog specialityDialog = new SpecialityDialog(currentFacultyModel);
                specialityDialog.show();
            }
        });

        final Button editSpecialityButton = new Button(MESSAGES.edit(), IconHelper.createStyle("editButton-icon"));
        editSpecialityButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                final SpecialityModel specialityModel = specialitiesGrid.getSelectionModel().getSelectedItem();

                if (specialityModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectSpecialityPlease());
                } else {
                    final SpecialityDialog specialityDialog = new SpecialityDialog(specialityModel);
                    specialityDialog.show();
                }
            }
        });

        final Button removeSpecialityButton = new Button(MESSAGES.delete(), IconHelper.createStyle("removeButton-icon"));
        removeSpecialityButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<SpecialityModel> specialityList = specialitiesGrid.getSelectionModel().getSelectedItems();

                if (specialityList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectSpecialitiesPlease());
                } else {
                    final StringBuilder message = new StringBuilder(MESSAGES.deleteSpecialitiesConfirm());
                    message.append("<br>").append("<br>");

                    for (int i = 0; i < specialityList.size(); ++i) {
                        message.append(i + 1).append(". ").append(specialityList.get(i).getFullName()).append("<br>");
                    }

                    MessageBox.confirm(
                            MESSAGES.deletingSpecialities(),
                            message.toString(),
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        delete(specialityList);
                                    }
                                }
                            });
                }
            }
        });

        final ToolBar specialityGridToolBar = new ToolBar();
        specialityGridToolBar.add(addSpecialityButton);
        specialityGridToolBar.add(new SeparatorToolItem());
        specialityGridToolBar.add(editSpecialityButton);
        specialityGridToolBar.add(new SeparatorToolItem());
        specialityGridToolBar.add(removeSpecialityButton);

        final LayoutContainer fullNameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        fullNameLayoutContainer.add(fullNameTextField, FormUtil.wFormData);

        final LayoutContainer nameLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        nameLayoutContainer.add(nameTextField, FormUtil.wFormData);

        final LayoutContainer deansLayoutContainer = new LayoutContainer(new FormLayout(FormPanel.LabelAlign.TOP));
        deansLayoutContainer.add(deansComboBox, FormUtil.wFormData);

        final Button saveButton = new Button(MESSAGES.save(), IconHelper.createStyle("saveButton-icon"));
        saveButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                currentFacultyModel.setFullName(fullNameTextField.getValue());
                currentFacultyModel.setName(nameTextField.getValue());
                currentFacultyModel.setDean(deansComboBox.getValue());

                FacultyService.Util.getInstance().saveOrUpdate(
                        currentFacultyModel,
                        new BaseAsyncCallback<FacultyModel>() {
                            @Override
                            public void onSuccess(final FacultyModel facultyModel) {
                                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.editFacultySuccessMessage());
                                Dispatcher.forwardEvent(AdminEvents.FacultyChanged, facultyModel);
                            }
                        });
            }
        });

        final Button cancelButton = new Button(MESSAGES.cancel(), IconHelper.createStyle("cancelButton-icon"));
        cancelButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                bind(currentFacultyModel);
            }
        });

        final ContentPanel facultyDataPanel = new ContentPanel(new RowLayout(Style.Orientation.HORIZONTAL));
        facultyDataPanel.setFrame(true);
        facultyDataPanel.setHeaderVisible(false);

        facultyDataPanel.add(fullNameLayoutContainer, new RowData(1, 1, new Margins(0, 5, 0, 5)));
        facultyDataPanel.add(nameLayoutContainer, new RowData(200, 1, new Margins(0, 5, 0, 5)));
        facultyDataPanel.add(deansLayoutContainer, new RowData(300, 1, new Margins(0, 5, 0, 5)));

        facultyDataPanel.addButton(saveButton);
        facultyDataPanel.addButton(cancelButton);

        final ContentPanel specialityGridPanel = new ContentPanel(new FitLayout());
        specialityGridPanel.setHeading(MESSAGES.specialityListHeading());
        specialityGridPanel.setTopComponent(specialityGridToolBar);
        specialityGridPanel.add(specialitiesGrid);

        add(facultyDataPanel, new RowData(1, 100));
        add(specialityGridPanel, new RowData(1, 1));
    }

    public void reload(final FacultyModel facultyModel) {
        this.currentFacultyModel = facultyModel;

        reload();
    }

    public void reload() {
        Validate.notNull(currentFacultyModel, "Current faculty is null in faculty panel.");

        bind(currentFacultyModel);

        specialitiesGrid.reload(currentFacultyModel.getId());
    }

    private void delete(final List<SpecialityModel> specialityList) {
        final List<Long> specialityIdList = new ArrayList<Long>(specialityList.size());

        for (SpecialityModel specialityModel : specialityList) {
            specialityIdList.add(specialityModel.getId());
        }

        mask(MESSAGES.deletingSelectedSpecialities());

        SpecialityService.Util.getInstance().deleteSpecialities(
                specialityIdList,
                new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        for (SpecialityModel specialityModel : specialityList) {
                            specialitiesGrid.getStore().remove(specialityModel);
                        }

                        unmask();

                        Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.deleteSpecialitiesSuccess());
                        Dispatcher.forwardEvent(AdminEvents.SpecialitiesDeleted);
                    }
                });
    }

    private void bind(final FacultyModel facultyModel) {
        fullNameTextField.setValue(facultyModel.getFullName());
        nameTextField.setValue(facultyModel.getName());
        deansComboBox.setValue(facultyModel.getDean());
    }
}
