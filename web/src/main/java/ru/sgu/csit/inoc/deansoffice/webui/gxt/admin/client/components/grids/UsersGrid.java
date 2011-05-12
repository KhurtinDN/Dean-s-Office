package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.UserService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.UserModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/19/11
 * Time: 11:15 AM
 */
public class UsersGrid extends Grid<UserModel> {
    public UsersGrid() {
        RpcProxy<List<UserModel>> proxy = new RpcProxy<List<UserModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<UserModel>> listAsyncCallback) {
                UserService.Util.getInstance().loadUsers(listAsyncCallback);
            }
        };

        ListLoader<ListLoadResult<UserModel>> loader = new BaseListLoader<ListLoadResult<UserModel>>(proxy);

        this.store = new ListStore<UserModel>(loader);
        this.cm = createColumnModel();
        this.view = new GridView();
        disabledStyle = null;
        baseStyle = "x-grid-panel";
        setSelectionModel(new GridSelectionModel<UserModel>());
        disableTextSelection(true);

        setBorders(true);
        setLoadMask(true);
        setAutoExpandColumn("fullName");
        setAutoExpandMax(2000);
    }

    private ColumnModel createColumnModel() {
        ColumnConfig nnColumnConfig = new ColumnConfig("nn", "#", 40);
        nnColumnConfig.setSortable(false);
        nnColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return rowIndex + 1;
            }
        });

        TextField<String> fullNameTextField = new TextField<String>();
        fullNameTextField.setAllowBlank(false);

        TextField<String> loginTextField = new TextField<String>();
        loginTextField.setAllowBlank(false);

        TextField<String> passwordTextField = new TextField<String>();
        passwordTextField.setAllowBlank(false);

        ColumnConfig fullNameColumnConfig = new ColumnConfig("fullName", "Полное имя", 300);
        fullNameColumnConfig.setEditor(new CellEditor(fullNameTextField));

        ColumnConfig loginColumnConfig = new ColumnConfig("login", "Логин", 200);
        loginColumnConfig.setEditor(new CellEditor(loginTextField));

        ColumnConfig passwordColumnConfig = new ColumnConfig("password", "Пароль", 200);
        passwordColumnConfig.setEditor(new CellEditor(passwordTextField));

        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(nnColumnConfig);
        columns.add(fullNameColumnConfig);
        columns.add(loginColumnConfig);
        columns.add(passwordColumnConfig);

        return new ColumnModel(columns);
    }

    public void reload() {
        store.getLoader().load();
    }
}
