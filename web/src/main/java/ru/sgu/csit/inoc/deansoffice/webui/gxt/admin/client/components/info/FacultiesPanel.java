package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info;

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.FacultiesGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/15/11
 * Time: 10:44 PM
 */
public class FacultiesPanel extends FormPanel {
    private FacultiesGrid facultiesGrid = new FacultiesGrid();
    private RowEditor<FacultyModel> rowEditor = new RowEditor<FacultyModel>();

    public FacultiesPanel() {
        setHeading("Факультеты");
        setLayout(new FitLayout());
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        rowEditor.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);
        rowEditor.addListener(Events.AfterEdit, new Listener<RowEditorEvent>() {
            @Override
            public void handleEvent(RowEditorEvent rowEditorEvent) {
                facultiesGrid.mask("Сохраниние измененного факультета");
                FacultyModel facultyModel = facultiesGrid.getStore().getAt(rowEditorEvent.getRowIndex());

                FacultyService.Util.getInstance().updateFaculty(facultyModel, new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        facultiesGrid.getStore().commitChanges();

                        facultiesGrid.unmask();

                        Dispatcher.forwardEvent(CommonEvents.Info, "Факультет успешно изменен!");
                        Dispatcher.forwardEvent(AdminEvents.FacultyChanged);
                    }
                });
            }
        });

        facultiesGrid.addPlugin(rowEditor);

        Button addFacultyButton = new Button("Добавить", IconHelper.createStyle("addButton-icon"));
        addFacultyButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                rowEditor.stopEditing(false);
                facultiesGrid.mask("Добавление нового факультета");

                FacultyService.Util.getInstance().createFaculty(new BaseAsyncCallback<FacultyModel>() {
                    @Override
                    public void onSuccess(FacultyModel facultyModel) {
                        facultiesGrid.getStore().add(facultyModel);
                        rowEditor.startEditing(facultiesGrid.getStore().indexOf(facultyModel), true);

                        facultiesGrid.unmask();

                        Dispatcher.forwardEvent(CommonEvents.Info, "Факультет успешно добавлен!");
                        Dispatcher.forwardEvent(AdminEvents.FacultyAdded);
                    }
                });
            }
        });

        Button editFacultyButton = new Button("Редактировать", IconHelper.createStyle("editButton-icon"));
        editFacultyButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                FacultyModel facultyModel = facultiesGrid.getSelectionModel().getSelectedItem();

                if (facultyModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, Факультет!");
                } else {
                    rowEditor.startEditing(facultiesGrid.getStore().indexOf(facultyModel), true);
                }
            }
        });

        Button removeFacultyButton = new Button("Удалить", IconHelper.createStyle("removeButton-icon"));
        removeFacultyButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<FacultyModel> facultyModelList = facultiesGrid.getSelectionModel().getSelectedItems();

                if (facultyModelList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, Факультеты!");
                } else {
                    MessageBox.confirm("Удаление Факультетов", "Вы действително хотите удалить выбранные факультеты?",
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        rowEditor.stopEditing(false);
                                        facultiesGrid.mask("Удаление выбранных факультетов");

                                        final List<Long> facultyIdList = new ArrayList<Long>(facultyModelList.size());

                                        for (FacultyModel facultyModel : facultyModelList) {
                                            facultyIdList.add(facultyModel.getId());
                                        }

                                        FacultyService.Util.getInstance().deleteFaculties(facultyIdList, new BaseAsyncCallback<Void>() {
                                            @Override
                                            public void onSuccess(Void result) {
                                                for (FacultyModel facultyModel : facultyModelList) {
                                                    facultiesGrid.getStore().remove(facultyModel);
                                                }

                                                facultiesGrid.unmask();

                                                Dispatcher.forwardEvent(CommonEvents.Info, "Факультеты успешно удалены!");
                                                Dispatcher.forwardEvent(AdminEvents.FacultiesDeleted);
                                            }
                                        });
                                    }
                                }
                            });
                }
            }
        });

        ContentPanel facultiesGridPanel = new ContentPanel(new FitLayout());
        facultiesGridPanel.setHeading("Список факультетов");

        facultiesGridPanel.add(facultiesGrid);

        facultiesGridPanel.addButton(addFacultyButton);
        facultiesGridPanel.addButton(editFacultyButton);
        facultiesGridPanel.addButton(removeFacultyButton);

        add(facultiesGridPanel);

        facultiesGrid.reload();
    }
}
