package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.FacultyService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.EmployeeService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.EmployeeModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.FacultyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Khurtin
 */
public class FacultiesGrid extends Grid<FacultyModel> {

    public FacultiesGrid() {
        RpcProxy<List<FacultyModel>> proxy = new RpcProxy<List<FacultyModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<FacultyModel>> listAsyncCallback) {
                FacultyService.Util.getInstance().loadFaculties(listAsyncCallback);
            }
        };

        ListLoader<ListLoadResult<FacultyModel>> loader = new BaseListLoader<ListLoadResult<FacultyModel>>(proxy);

        this.store = new ListStore<FacultyModel>(loader);
        this.cm = createColumnModel();
        this.view = new GridView();
        disabledStyle = null;
        baseStyle = "x-grid-panel";
        setSelectionModel(new GridSelectionModel<FacultyModel>());
        disableTextSelection(true);

        setBorders(true);
        setLoadMask(true);
        setAutoExpandColumn("fullName");
        setAutoExpandMax(2000);
    }

    @Override
    protected void onRender(Element target, int index) {
        super.onRender(target, index);

        reload();
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

        ColumnConfig fullNameColumnConfig = new ColumnConfig("fullName", "Полное имя", 300);

        TextField<String> fullNameTextField = new TextField<String>();
        fullNameTextField.setAllowBlank(false);

        fullNameColumnConfig.setEditor(new CellEditor(fullNameTextField));

        ColumnConfig nameColumnConfig = new ColumnConfig("name", "Имя", 200);

        TextField<String> nameTextField = new TextField<String>();
        nameTextField.setAllowBlank(false);

        nameColumnConfig.setEditor(new CellEditor(nameTextField));

        ColumnConfig deanColumnConfig = new ColumnConfig("dean", "Декан", 300);
        deanColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                FacultyModel facultyModel = (FacultyModel) model;
                EmployeeModel dean = facultyModel.getDean();
                return dean == null ? null : dean.getFullName();
            }
        });

        final ComboBox<EmployeeModel> deansComboBox = new ComboBox<EmployeeModel>();
        deansComboBox.setAllowBlank(false);
        deansComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);
        deansComboBox.setDisplayField("fullName");

        RpcProxy<List<EmployeeModel>> proxy = new RpcProxy<List<EmployeeModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<EmployeeModel>> listAsyncCallback) {
                EmployeeService.Util.getInstance().loadDeanList(listAsyncCallback);
            }
        };

        ListLoader<ListLoadResult<EmployeeModel>> loader = new BaseListLoader<ListLoadResult<EmployeeModel>>(proxy);
        deansComboBox.setStore(new ListStore<EmployeeModel>(loader));

        deanColumnConfig.setEditor(new CellEditor(deansComboBox));

        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(nnColumnConfig);
        columns.add(fullNameColumnConfig);
        columns.add(nameColumnConfig);
        columns.add(deanColumnConfig);

        return new ColumnModel(columns);
    }

    public void reload() {
        store.getLoader().load();
    }
}
