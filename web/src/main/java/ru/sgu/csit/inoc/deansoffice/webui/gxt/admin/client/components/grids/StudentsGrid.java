package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.components.grids;

import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.StudentsService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.util.StudentModelUtil;

import java.util.ArrayList;
import java.util.List;

import static ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.AdminConstants.MESSAGES;

/**
 * @author Denis Khurtin
 */
public class StudentsGrid extends Grid<StudentModel> {
    private Long groupId;

    public StudentsGrid() {
        RpcProxy<List<StudentModel>> proxy = new RpcProxy<List<StudentModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<StudentModel>> listAsyncCallback) {
                StudentsService.Util.getInstance().loadStudentList(groupId, listAsyncCallback);
            }
        };

        ListLoader<ListLoadResult<SpecialityModel>> loader = new BaseListLoader<ListLoadResult<SpecialityModel>>(proxy);

        this.store = new ListStore<StudentModel>(loader);
        this.cm = createColumnModel();
        this.view = new GridView();
        disabledStyle = null;
        baseStyle = "x-grid-panel";
        setSelectionModel(new GridSelectionModel<StudentModel>());
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

        ColumnConfig nameColumnConfig = new ColumnConfig("fullName", MESSAGES.name(), 200);

        ColumnConfig studentIdNumberColumnConfig = new ColumnConfig("studentIdNumber", MESSAGES.studentIdNumber(), 150);

        ColumnConfig divisionColumnConfig = new ColumnConfig("division", MESSAGES.division(), 150);
        divisionColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return StudentModelUtil.divisionToString(((StudentModel) model).getDivision());
            }
        });

        ColumnConfig studyFormColumnConfig = new ColumnConfig("studyForm", MESSAGES.studyForm(), 150);
        studyFormColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return StudentModelUtil.studyFormToString(((StudentModel) model).getStudyForm());
            }
        });

        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(nnColumnConfig);
        columns.add(nameColumnConfig);
        columns.add(studentIdNumberColumnConfig);
        columns.add(divisionColumnConfig);
        columns.add(studyFormColumnConfig);

        return new ColumnModel(columns);
    }

    public void reload(final Long groupId) {
        this.groupId = groupId;
        store.getLoader().load();
    }
}
