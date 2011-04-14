package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components;

import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ListField;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.UserService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.UserModel;

import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 3:37 PM
 */
public class UsersContentPanel extends ContentPanel {
    private ListField<UserModel> userList = new ListField<UserModel>();

    public UsersContentPanel() {
        setHeading("Пользователи");
        setLayout(new FitLayout());

        Button addUserButton = new Button("Добавить", IconHelper.createStyle("addButton-icon"));
        addUserButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                ;
            }
        });
        addButton(addUserButton);

        Button removeUserButton = new Button("Удалить", IconHelper.createStyle("removeButton-icon"));
        removeUserButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                ;
            }
        });
        addButton(removeUserButton);
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        RpcProxy<List<UserModel>> proxy = new RpcProxy<List<UserModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<UserModel>> async) {
                UserService.App.getInstance().loadUsers(async);
            }
        };

        ListLoader<ListLoadResult<UserModel>> loader = new BaseListLoader<ListLoadResult<UserModel>>(proxy);
        ListStore<UserModel> userListStore = new ListStore<UserModel>(loader);

        loader.load();

        userList.setStore(userListStore);
        userList.setTemplate(getTemplate());
    }

    private String getTemplate() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<tpl for=\".\">");
		stringBuilder.append("<div class='x-combo-list-item'>{firstName} {lastName} (<b>{login}</b>)</div>");
		stringBuilder.append("</tpl>");
		return stringBuilder.toString();
	}
}
