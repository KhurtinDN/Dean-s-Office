package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info;

import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.dialogs.FacultyDialog;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.FacultiesGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;

import java.util.ArrayList;
import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class FacultiesPanel extends ContentPanel {
    private final FacultiesGrid facultiesGrid = new FacultiesGrid();

    public FacultiesPanel() {
        setHeading("Факультеты");
        setLayout(new FitLayout());
    }

    @Override
    protected void onRender(final Element target, final int index) {
        super.onRender(target, index);

        final Button addFacultyButton = new Button(MESSAGES.add(), IconHelper.createStyle("addButton-icon"));
        addFacultyButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                final FacultyDialog facultyDialog = new FacultyDialog();
                facultyDialog.show();
            }
        });

        final Button editFacultyButton = new Button(MESSAGES.edit(), IconHelper.createStyle("editButton-icon"));
        editFacultyButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {

                final FacultyModel facultyModel = facultiesGrid.getSelectionModel().getSelectedItem();

                if (facultyModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectFacultyPlease());
                } else {
                    final FacultyDialog facultyDialog = new FacultyDialog(facultyModel);
                    facultyDialog.show();
                }
            }
        });

        final Button removeFacultyButton = new Button(MESSAGES.delete(), IconHelper.createStyle("removeButton-icon"));
        removeFacultyButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                final List<FacultyModel> facultyModelList = facultiesGrid.getSelectionModel().getSelectedItems();

                if (facultyModelList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectFacultiesPlease());
                } else {
                    final StringBuilder message = new StringBuilder(MESSAGES.deleteFacultiesConfirm());
                    message.append("<br>").append("<br>");

                    for (int i = 0; i < facultyModelList.size(); ++i) {
                        message.append(i + 1).append(". ").append(facultyModelList.get(i).getFullName()).append("<br>");
                    }

                    MessageBox.confirm(
                            MESSAGES.deletingFaculties(),
                            message.toString(),
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(final MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        delete(facultyModelList);
                                    }
                                }
                            });
                }
            }
        });

        final ToolBar facultiesGridToolBar = new ToolBar();
        facultiesGridToolBar.add(addFacultyButton);
        facultiesGridToolBar.add(new SeparatorToolItem());
        facultiesGridToolBar.add(editFacultyButton);
        facultiesGridToolBar.add(new SeparatorToolItem());
        facultiesGridToolBar.add(removeFacultyButton);

        final ContentPanel facultiesGridPanel = new ContentPanel(new FitLayout());
        facultiesGridPanel.setHeaderVisible(false);
        facultiesGridPanel.setTopComponent(facultiesGridToolBar);
        facultiesGridPanel.add(facultiesGrid);

        add(facultiesGridPanel);
    }

    public void reload() {
        facultiesGrid.reload();
    }

    private void delete(final List<FacultyModel> facultyModelList) {
        final List<Long> facultyIdList = new ArrayList<Long>(facultyModelList.size());

        for (final FacultyModel facultyModel : facultyModelList) {
            facultyIdList.add(facultyModel.getId());
        }

        mask(MESSAGES.deletingSelectedFaculties());

        FacultyService.Util.getInstance().deleteFaculties(facultyIdList, new BaseAsyncCallback<Void>() {
            @Override
            public void onSuccess(final Void result) {
                for (FacultyModel facultyModel : facultyModelList) {
                    facultiesGrid.getStore().remove(facultyModel);
                }

                unmask();

                Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.deleteFacultiesSuccess());
                Dispatcher.forwardEvent(AdminEvents.FacultiesDeleted);
            }
        });
    }
}
