package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.EmployeeService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;

import java.util.ArrayList;
import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class EmployeeGrid extends Grid<EmployeeModel> {
    public EmployeeGrid() {
        final RpcProxy<List<EmployeeModel>> proxy = new RpcProxy<List<EmployeeModel>>() {
            @Override
            protected void load(final Object loadConfig, AsyncCallback<List<EmployeeModel>> listAsyncCallback) {
                EmployeeService.Util.getInstance().loadStaffList(listAsyncCallback);
            }
        };

        final ListLoader<ListLoadResult<EmployeeModel>> loader = new BaseListLoader<ListLoadResult<EmployeeModel>>(proxy);

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
        final ColumnConfig nnColumnConfig = new ColumnConfig("nn", "#", 40);
        nnColumnConfig.setSortable(false);
        nnColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return rowIndex + 1;
            }
        });

        final TextField<String> fullNameTextField = new TextField<String>();
        fullNameTextField.setAllowBlank(false);

        final TextField<String> positionTextField = new TextField<String>();
        positionTextField.setAllowBlank(false);

        final TextField<String> degreeTextField = new TextField<String>();
        degreeTextField.setAllowBlank(false);

        final ColumnConfig fullNameColumnConfig = new ColumnConfig("fullName", MESSAGES.fullName(), 300);
        fullNameColumnConfig.setEditor(new CellEditor(fullNameTextField));

        final ColumnConfig positionColumnConfig = new ColumnConfig("position", MESSAGES.position(), 300);
        positionColumnConfig.setEditor(new CellEditor(positionTextField));

        final ColumnConfig degreeColumnConfig = new ColumnConfig("degree", MESSAGES.degree(), 300);
        degreeColumnConfig.setEditor(new CellEditor(degreeTextField));

        final List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(nnColumnConfig);
        columns.add(fullNameColumnConfig);
        columns.add(positionColumnConfig);
        columns.add(degreeColumnConfig);

        return new ColumnModel(columns);
    }

    public void reload() {
        store.getLoader().load();
    }
}
