package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.StaffService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/19/11
 * Time: 11:15 AM
 */
public class StaffGrid extends Grid<EmployeeModel> {
    public StaffGrid() {
        RpcProxy<List<EmployeeModel>> proxy = new RpcProxy<List<EmployeeModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<EmployeeModel>> listAsyncCallback) {
                StaffService.Util.getInstance().loadStaffList(listAsyncCallback);
            }
        };

        ListLoader<ListLoadResult<EmployeeModel>> loader = new BaseListLoader<ListLoadResult<EmployeeModel>>(proxy);

        this.store = new ListStore<EmployeeModel>(loader);
        this.cm = createColumnModel();
        this.view = new GridView();
        disabledStyle = null;
        baseStyle = "x-grid-panel";
        setSelectionModel(new GridSelectionModel<EmployeeModel>());
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

        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(nnColumnConfig);
        columns.add(fullNameColumnConfig);

        return new ColumnModel(columns);
    }

    public void reload() {
        store.getLoader().load();
    }
}
