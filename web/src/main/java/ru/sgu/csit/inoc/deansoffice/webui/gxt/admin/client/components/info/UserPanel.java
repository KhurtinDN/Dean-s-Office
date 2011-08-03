package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.info;

import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.RowEditor;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.user.client.Element;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.UsersGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.UserService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.UserModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/15/11
 * Time: 3:11 PM
 */
public class UserPanel extends ContentPanel {
    private UsersGrid usersGrid = new UsersGrid();

    public UserPanel() {
        setHeading("Пользователи системы");
        setLayout(new FitLayout());

        final RowEditor<UserModel> rowEditor = new RowEditor<UserModel>();
        rowEditor.setClicksToEdit(EditorGrid.ClicksToEdit.TWO);
        rowEditor.addListener(Events.AfterEdit, new Listener<RowEditorEvent>() {
            @Override
            public void handleEvent(RowEditorEvent rowEditorEvent) {
                mask("Сохраниние измененного пользователя");

                UserModel userModel = (UserModel) rowEditorEvent.getRecord().getModel();

                UserService.Util.getInstance().update(userModel, new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        usersGrid.getStore().commitChanges();
                        unmask();
                        Dispatcher.forwardEvent(CommonEvents.Info, "Пользователь успешно изменен!");
                        Dispatcher.forwardEvent(AdminEvents.UserChanged);
                    }
                });
            }
        });

        usersGrid.addPlugin(rowEditor);

        Button addUserButton = new Button("Добавить", IconHelper.createStyle("addButton-icon"));
        addUserButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                rowEditor.stopEditing(false);
                mask("Добавление нового пользователя");

                UserService.Util.getInstance().create(new BaseAsyncCallback<UserModel>() {
                    @Override
                    public void onSuccess(UserModel userModel) {
                        usersGrid.getStore().add(userModel);
                        rowEditor.startEditing(usersGrid.getStore().indexOf(userModel), true);
                        unmask();

                        Dispatcher.forwardEvent(CommonEvents.Info, "Пользователь успешно добавлен!");
                        Dispatcher.forwardEvent(AdminEvents.UserAdded);
                    }
                });
            }
        });

        Button editUserButton = new Button("Редактировать", IconHelper.createStyle("editButton-icon"));
        editUserButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                UserModel userModel = usersGrid.getSelectionModel().getSelectedItem();

                if (userModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, пользователя!");
                } else {
                    rowEditor.startEditing(usersGrid.getStore().indexOf(userModel), true);
                }
            }
        });

        Button removeUserButton = new Button("Удалить", IconHelper.createStyle("removeButton-icon"));
        removeUserButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<UserModel> userList = usersGrid.getSelectionModel().getSelectedItems();

                if (userList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, "Выберите, пожалуйста, пользователей!");
                } else {
                    final List<Long> userIdList = new ArrayList<Long>(userList.size());

                    for (UserModel userModel : userList) {
                        userIdList.add(userModel.getId());
                    }

                    MessageBox.confirm("Удаление пользователей",
                            "Вы действително хотите удалить выбранных пользователей?",
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        rowEditor.stopEditing(false);
                                        mask("Удаление выбранных пользователей");

                                        UserService.Util.getInstance().delete(userIdList,
                                                new BaseAsyncCallback<Void>() {
                                                    @Override
                                                    public void onSuccess(Void result) {
                                                        for (UserModel userModel : userList) {
                                                            usersGrid.getStore().remove(userModel);
                                                        }

                                                        unmask();
                                                        Dispatcher.forwardEvent(CommonEvents.Info, "Пользователи успешно удалены!");
                                                        Dispatcher.forwardEvent(AdminEvents.UserDeleted);
                                                    }
                                                });
                                    }
                                }
                            });
                }
            }
        });

        ToolBar userGridToolBar = new ToolBar();
        userGridToolBar.add(addUserButton);
        userGridToolBar.add(new SeparatorToolItem());
        userGridToolBar.add(editUserButton);
        userGridToolBar.add(new SeparatorToolItem());
        userGridToolBar.add(removeUserButton);

        setTopComponent(userGridToolBar);

        add(usersGrid);
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        usersGrid.reload();
    }
}
