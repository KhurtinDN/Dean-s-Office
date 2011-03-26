package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.orders;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.LabelField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.FormUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.ReferenceService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.utils.ReferenceModelUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.utils.StudentModelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 9:58 AM
 */
public class ReferenceQueueWindow extends Window {
    private Grid<ReferenceModel> grid;
    private ListLoader<ListLoadResult<ReferenceModel>> loader;

    private ContentPanel mainContentPanel = createMainContentPanel();
    private ReferenceInfoPanel referenceInfoPanel = new ReferenceInfoPanel();

    public ReferenceQueueWindow() {
        setHeading("Очередь справок");
        setSize(1200, 600);
        setModal(true);
        setBlinkModal(true);


        grid.getSelectionModel().addListener(Events.SelectionChange,
                new Listener<SelectionChangedEvent<ReferenceModel>>() {
                    @Override
                    public void handleEvent(SelectionChangedEvent<ReferenceModel> be) {
                        if (be.getSelection().size() == 1) {
                            referenceInfoPanel.bind(be.getSelectedItem());
                        } else {
                            referenceInfoPanel.unbind();
                        }
                    }
                });
    }

    @Override
    protected void onRender(Element parent, int pos) {
        super.onRender(parent, pos);

        setLayout(new BorderLayout());

        BorderLayoutData centerLayoutData = new BorderLayoutData(Style.LayoutRegion.CENTER);

        BorderLayoutData eastLayoutData = new BorderLayoutData(Style.LayoutRegion.EAST, 350);
        eastLayoutData.setCollapsible(true);
        eastLayoutData.setSplit(true);

        add(mainContentPanel, centerLayoutData);
        add(referenceInfoPanel, eastLayoutData);
    }

    private ContentPanel createMainContentPanel() {
        ContentPanel contentPanel = new ContentPanel(new FitLayout());
        contentPanel.setHeading("Справки");

        RpcProxy<List<ReferenceModel>> proxy = new RpcProxy<List<ReferenceModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<ReferenceModel>> listAsyncCallback) {
                ReferenceService.App.getInstance().loadReferences(true, listAsyncCallback);
            }
        };
        loader = new BaseListLoader<ListLoadResult<ReferenceModel>>(proxy);
        ListStore<ReferenceModel> referenceStore = new ListStore<ReferenceModel>(loader);

        CheckBoxSelectionModel<ReferenceModel> checkBoxSelectionModel = new CheckBoxSelectionModel<ReferenceModel>();
        checkBoxSelectionModel.setSelectionMode(Style.SelectionMode.MULTI);

        grid = new Grid<ReferenceModel>(referenceStore, createColumnModel(checkBoxSelectionModel.getColumn()));
        grid.setBorders(true);
        grid.setAutoExpandColumn("name");
        grid.setSelectionModel(checkBoxSelectionModel);
        grid.addPlugin(checkBoxSelectionModel);

        contentPanel.add(grid);

        contentPanel.addButton(new Button("Создать новый", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(AppEvents.AddNewOrderCall);
            }
        }));

        contentPanel.addButton(new Button("Изменить выбранный", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(AppEvents.EditOrderCall);
            }
        }));

        return contentPanel;
    }

    private ColumnModel createColumnModel(ColumnConfig pluginColumn) {
        ColumnConfig nnColumnConfig = new ColumnConfig("nn", "#", 40);
        nnColumnConfig.setSortable(false);
        nnColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return rowIndex + 1;
            }
        });

        ColumnConfig registrationDateColumnConfig = new ColumnConfig("registrationDate", "Добавлена", 80);
        registrationDateColumnConfig.setDateTimeFormat(
                DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_MEDIUM));

        ColumnConfig nameColumnConfig = new ColumnConfig("name", "ФИО", 200);
        nameColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                StudentModel studentModel = ((ReferenceModel) model).getStudent();
                return studentModel.getFullName();
            }
        });

        ColumnConfig typeColumnConfig = new ColumnConfig("type", "Тип", 80);
        typeColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return ReferenceModelUtil.typeToString( ((ReferenceModel) model).getType() );
            }
        });

        ColumnConfig groupNameColumnConfig = new ColumnConfig("groupName", "Группа", 80);
        groupNameColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return ((ReferenceModel)model).getStudent().getGroupName();
            }
        });

        ColumnConfig statusColumnConfig = new ColumnConfig("status", "Статус", 110);
        statusColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return ReferenceModelUtil.statusToString( ((ReferenceModel) model).getStatus() );
            }
        });

        ColumnConfig issueDateColumnConfig = new ColumnConfig("issueDate", "Выдана", 80);
        issueDateColumnConfig.setDateTimeFormat(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_MEDIUM));

        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

        columns.add(pluginColumn);
        columns.add(nnColumnConfig);
        columns.add(registrationDateColumnConfig);
        columns.add(nameColumnConfig);
        columns.add(typeColumnConfig);
        columns.add(groupNameColumnConfig);
        columns.add(statusColumnConfig);
        columns.add(issueDateColumnConfig);

        return new ColumnModel(columns);
    }

    @Override
    public void show() {
        super.show();

        loader.load();
    }

    private class ReferenceInfoPanel extends FormPanel {
        private ReferenceModel referenceModel;

        private LabelField registrationDateLabelField = new LabelField();
        private LabelField nameLabelField = new LabelField();
        private LabelField groupNameLabelField = new LabelField();
        private LabelField studyFormLabelField = new LabelField();
        private LabelField typeLabelField = new LabelField();
        private TextField<String> destinationTextField = new TextField<String>();
        private LabelField statusLabelField = new LabelField();
        private LabelField issueDateLabelField = new LabelField();

        private DateTimeFormat dtf = DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_MEDIUM);

        private ReferenceInfoPanel() {
            setHeading("Информация о справке");
            setLabelWidth(120);

            registrationDateLabelField.setName("registrationDate");
            registrationDateLabelField.setFieldLabel("Дата добавления");
            registrationDateLabelField.setLabelStyle("font-weight: bold");

            nameLabelField.setName("name");
            nameLabelField.setFieldLabel("ФИО");
            nameLabelField.setLabelStyle("font-weight: bold");

            groupNameLabelField.setName("groupName");
            groupNameLabelField.setFieldLabel("Группа");
            groupNameLabelField.setLabelStyle("font-weight: bold");

            studyFormLabelField.setName("studyForm");
            studyFormLabelField.setFieldLabel("Форма обучения");
            studyFormLabelField.setLabelStyle("font-weight: bold");

            typeLabelField.setName("type");
            typeLabelField.setFieldLabel("Тип");
            typeLabelField.setLabelStyle("font-weight: bold");

            destinationTextField.setName("destination");
            destinationTextField.setFieldLabel("Назначение");
            destinationTextField.setLabelStyle("font-weight: bold");

//            destinationTextField.addListener(Events.Change, new Listener<>); todo

            statusLabelField.setName("status");
            statusLabelField.setFieldLabel("Статус");
            statusLabelField.setLabelStyle("font-weight: bold");

            issueDateLabelField.setName("issueDate");
            issueDateLabelField.setFieldLabel("Дата выдачи");
            issueDateLabelField.setLabelStyle("font-weight: bold");
        }

        @Override
        protected void onRender(Element target, int index) {
            super.onRender(target, index);

            add(registrationDateLabelField, FormUtil.wh5FormData);
            add(nameLabelField, FormUtil.wh5FormData);
            add(groupNameLabelField, FormUtil.wh5FormData);
            add(studyFormLabelField, FormUtil.wh5FormData);
            add(typeLabelField, FormUtil.wh5FormData);
            add(destinationTextField, FormUtil.wh5FormData);
            add(statusLabelField, FormUtil.wh5FormData);
            add(issueDateLabelField, FormUtil.wh5FormData);
        }

        public void bind(ReferenceModel referenceModel) {
            bind(referenceModel, false);
        }

        public void bind(ReferenceModel referenceModel, boolean auto) {
//            if (auto) {
//                this.referenceModel
//            }
            registrationDateLabelField.setValue(dtf.format(referenceModel.getRegistrationDate()));
            nameLabelField.setValue(referenceModel.getStudent().getFullName());
            groupNameLabelField.setValue(referenceModel.getStudent().getGroupName());
            studyFormLabelField.setValue(StudentModelUtil.studyFormToString(referenceModel.getStudent().getStudyForm()));
            typeLabelField.setValue(ReferenceModelUtil.typeToString(referenceModel.getType()));
            destinationTextField.setValue(referenceModel.getDestination());
            statusLabelField.setValue(ReferenceModelUtil.statusToString(referenceModel.getStatus()));
            issueDateLabelField.setValue(dtf.format(referenceModel.getIssueDate()));

//            if (auto) {
//                destinationTextField.addListener(Events.);
//            }
        }

        public void unbind() {
            registrationDateLabelField.clear();
            nameLabelField.clear();
            groupNameLabelField.clear();
            studyFormLabelField.clear();
            typeLabelField.clear();
            destinationTextField.clear();
            statusLabelField.clear();
            issueDateLabelField.clear();
        }
    }
}
