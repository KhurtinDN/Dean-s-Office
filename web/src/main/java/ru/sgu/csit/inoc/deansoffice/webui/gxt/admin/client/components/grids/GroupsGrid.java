package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.GroupingStore;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.store.Store;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.GroupService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/19/11
 * Time: 12:16 PM
 */
public class GroupsGrid extends Grid<GroupModel> {
    private Long specialityId;

    public GroupsGrid() {
        RpcProxy<List<GroupModel>> proxy = new RpcProxy<List<GroupModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<GroupModel>> listAsyncCallback) {
                GroupService.Util.getInstance().loadGroups(specialityId, listAsyncCallback);
            }
        };

        ListLoader<ListLoadResult<GroupModel>> loader = new BaseListLoader<ListLoadResult<GroupModel>>(proxy);

        final GroupingStore<GroupModel> groupingStore = new GroupingStore<GroupModel>(loader);
        groupingStore.groupBy("course");

        final GroupingView groupingView = new GroupingView();
        groupingView.setForceFit(true);
        groupingView.setGroupRenderer(new GridGroupRenderer() {
            @Override
            public String render(GroupColumnData data) {
                String gr = data.models.size() == 1 ? "группа" : "группы";
                return data.group + " курс (" + data.models.size() + " " + gr + ")";
            }
        });

        this.store = groupingStore;
        this.cm = createColumnModel();
        this.view = groupingView;
        disabledStyle = null;
        baseStyle = "x-grid-panel";
        setSelectionModel(new GridSelectionModel<GroupModel>());
        disableTextSelection(true);

        groupingStore.addListener(Store.Update, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                groupingStore.groupBy("course", true);
            }
        });

        setBorders(true);
        setLoadMask(true);
        setAutoExpandColumn("name");
        setAutoExpandMax(2000);
    }

    private ColumnModel createColumnModel() {
        // Number column
        ColumnConfig nnColumnConfig = new ColumnConfig("nn", "#", 40);
        nnColumnConfig.setSortable(false);
        nnColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return rowIndex + 1;
            }
        });

        // Name column
        ColumnConfig nameColumnConfig = new ColumnConfig("name", "Имя", 200);

        TextField<String> nameTextField = new TextField<String>();
        nameTextField.setAllowBlank(false);

        nameColumnConfig.setEditor(new CellEditor(nameTextField));

        // Course column
        ColumnConfig codeColumnConfig = new ColumnConfig("course", "Курс", 200);

        NumberField codeNumberField = new NumberField();
        codeNumberField.setPropertyEditorType(Integer.class);
        codeNumberField.setAllowBlank(false);

        codeColumnConfig.setEditor(new CellEditor(codeNumberField));

        // Create and return ColumnModel
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(nnColumnConfig);
        columns.add(nameColumnConfig);
        columns.add(codeColumnConfig);

        return new ColumnModel(columns);
    }

    public void reload(Long specialityId) {
        this.specialityId = specialityId;
        store.getLoader().load();
    }
}
