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
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.dialogs.UserDialog;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids.UsersGrid;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.mvc.events.AdminEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.UserService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.UserModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;

import java.util.ArrayList;
import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class UserPanel extends ContentPanel {
    private final UsersGrid usersGrid = new UsersGrid();

    public UserPanel() {
        setHeading("Пользователи системы");
        setLayout(new FitLayout());
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        final Button addUserButton = new Button(MESSAGES.add(), IconHelper.createStyle("addButton-icon"));
        addUserButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {
                final UserDialog userDialog = new UserDialog();
                userDialog.show();
            }
        });

        final Button editUserButton = new Button(MESSAGES.edit(), IconHelper.createStyle("editButton-icon"));
        editUserButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(final ButtonEvent ce) {

                final UserModel userModel = usersGrid.getSelectionModel().getSelectedItem();

                if (userModel == null) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectUserPlease());
                } else {
                    final UserDialog userDialog = new UserDialog(userModel);
                    userDialog.show();
                }
            }
        });

        final Button removeUserButton = new Button(MESSAGES.delete(), IconHelper.createStyle("removeButton-icon"));
        removeUserButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<UserModel> userList = usersGrid.getSelectionModel().getSelectedItems();

                if (userList.isEmpty()) {
                    Dispatcher.forwardEvent(CommonEvents.InfoWithConfirmation, MESSAGES.selectUsersPlease());
                } else {
                    final StringBuilder message = new StringBuilder(MESSAGES.deleteFacultiesConfirm());
                    message.append("<br>").append("<br>");

                    for (int i = 0; i < userList.size(); ++i) {
                        message.append(i + 1).append(". ").append(userList.get(i).getFullName()).append("<br>");
                    }

                    MessageBox.confirm(
                            MESSAGES.deletingUsers(),
                            message.toString(),
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        delete(userList);
                                    }
                                }
                            });
                }
            }
        });

        final ToolBar userGridToolBar = new ToolBar();
        userGridToolBar.add(addUserButton);
        userGridToolBar.add(new SeparatorToolItem());
        userGridToolBar.add(editUserButton);
        userGridToolBar.add(new SeparatorToolItem());
        userGridToolBar.add(removeUserButton);

        final ContentPanel facultiesGridPanel = new ContentPanel(new FitLayout());
        facultiesGridPanel.setHeaderVisible(false);
        facultiesGridPanel.setTopComponent(userGridToolBar);
        facultiesGridPanel.add(usersGrid);

        add(facultiesGridPanel);
    }

    private void delete(final List<UserModel> userList) {
        final List<Long> userIdList = new ArrayList<Long>(userList.size());

        for (final UserModel userModel : userList) {
            userIdList.add(userModel.getId());
        }

        mask(MESSAGES.deletingSelectedUsers());

        UserService.Util.getInstance().delete(userIdList,
                new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(final Void result) {
                        for (final UserModel userModel : userList) {
                            usersGrid.getStore().remove(userModel);
                        }

                        unmask();

                        Dispatcher.forwardEvent(CommonEvents.Info, MESSAGES.deleteUsersSuccess());
                        Dispatcher.forwardEvent(AdminEvents.UsersDeleted);
                    }
                });
    }

    public void reload() {
        usersGrid.reload();
    }
}
