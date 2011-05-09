package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.SpecialityService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/19/11
 * Time: 11:15 AM
 */
public class SpecialitiesGrid extends Grid<SpecialityModel> {
    private Long facultyId;

    public SpecialitiesGrid() {
        RpcProxy<List<SpecialityModel>> proxy = new RpcProxy<List<SpecialityModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<SpecialityModel>> listAsyncCallback) {
                SpecialityService.Util.getInstance().loadSpecialities(facultyId, listAsyncCallback);
            }
        };

        ListLoader<ListLoadResult<SpecialityModel>> loader = new BaseListLoader<ListLoadResult<SpecialityModel>>(proxy);

        this.store = new ListStore<SpecialityModel>(loader);
        this.cm = createColumnModel();
        this.view = new GridView();
        disabledStyle = null;
        baseStyle = "x-grid-panel";
        setSelectionModel(new GridSelectionModel<SpecialityModel>());
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

        TextField<String> nameTextField = new TextField<String>();
        nameTextField.setAllowBlank(false);

        TextField<String> codeTextField = new TextField<String>();
        codeTextField.setAllowBlank(false);

        ColumnConfig fullNameColumnConfig = new ColumnConfig("fullName", "Полное имя", 300);
        fullNameColumnConfig.setEditor(new CellEditor(fullNameTextField));

        ColumnConfig nameColumnConfig = new ColumnConfig("name", "Имя", 200);
        nameColumnConfig.setEditor(new CellEditor(nameTextField));

        ColumnConfig codeColumnConfig = new ColumnConfig("code", "Код", 200);
        codeColumnConfig.setEditor(new CellEditor(codeTextField));

        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(nnColumnConfig);
        columns.add(fullNameColumnConfig);
        columns.add(nameColumnConfig);
        columns.add(codeColumnConfig);

        return new ColumnModel(columns);
    }

    public void reload(Long facultyId) {
        this.facultyId = facultyId;
        store.getLoader().load();
    }
}
