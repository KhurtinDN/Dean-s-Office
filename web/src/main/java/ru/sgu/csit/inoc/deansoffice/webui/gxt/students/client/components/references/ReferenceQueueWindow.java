package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.references;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.SimpleComboValue;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.toolbar.FillToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.ReferenceService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.utils.ReferenceModelUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/25/11
 * Time: 9:58 AM
 */
    public class ReferenceQueueWindow extends Window {
    private SimpleComboBox<LoadType> loadComboBox;
    private Grid<ReferenceModel> grid;

    private ContentPanel mainContentPanel;
    private ReferenceInfoPanel referenceInfoPanel;

    private boolean lastSelect = false;



    public ReferenceQueueWindow() {
        setHeading("Очередь справок");
        setSize(1200, 600);
        setModal(true);
        setBlinkModal(true);

        loadComboBox = new SimpleComboBox<LoadType>();
        loadComboBox.setEditable(false);
        loadComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);

        loadComboBox.add(ReferenceQueueWindow.LoadType.ALL);
        loadComboBox.add(ReferenceQueueWindow.LoadType.NOT_ISSUED);
        loadComboBox.add(ReferenceQueueWindow.LoadType.REGISTERED);
        loadComboBox.add(ReferenceQueueWindow.LoadType.PROCESSED);
        loadComboBox.add(ReferenceQueueWindow.LoadType.READY);
        loadComboBox.add(ReferenceQueueWindow.LoadType.ISSUED);

        loadComboBox.setSimpleValue(ReferenceQueueWindow.LoadType.NOT_ISSUED);

        mainContentPanel = createMainContentPanel();
        referenceInfoPanel = new ReferenceInfoPanel();

        mainContentPanel.setTopComponent(createToolBar());

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

    private ToolBar createToolBar() {
        ToolBar toolBar = new ToolBar();

        Button refreshButton = new Button("Обновить", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                reload();
            }
        });
        refreshButton.setIcon(IconHelper.createStyle("refresh-icon"));
        toolBar.add(refreshButton);

        toolBar.add(new FillToolItem());
        toolBar.add(loadComboBox);

        loadComboBox.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<LoadType>>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<SimpleComboValue<LoadType>> se) {
                reload();
            }
        });



        return toolBar;
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
                switch (loadComboBox.getSimpleValue()) {
                    case ALL:
                        ReferenceService.App.getInstance().loadAllReferences(listAsyncCallback);
                        break;
                    case NOT_ISSUED:
                        ReferenceService.App.getInstance().loadNotIssuedReferences(listAsyncCallback);
                        break;
                    case REGISTERED:
                        ReferenceService.App.getInstance().loadRegisteredReferences(listAsyncCallback);
                        break;
                    case PROCESSED:
                        ReferenceService.App.getInstance().loadProcessedReferences(listAsyncCallback);
                        break;
                    case READY:
                        ReferenceService.App.getInstance().loadReadyReferences(listAsyncCallback);
                        break;
                    case ISSUED:
                        ReferenceService.App.getInstance().loadIssuedReferences(listAsyncCallback);
                        break;
                }
            }
        };
        ListLoader<ListLoadResult<ReferenceModel>> loader = new BaseListLoader<ListLoadResult<ReferenceModel>>(proxy);
        final ListStore<ReferenceModel> referenceStore = new ListStore<ReferenceModel>(loader);

        loader.addLoadListener(new LoadListener() {
            @Override
            public void loaderLoad(LoadEvent le) {
                referenceStore.sort("registrationDate", Style.SortDir.ASC);

                if (lastSelect && referenceStore.getCount() > 0) {
                    lastSelect = false;
                    grid.getSelectionModel().select(referenceStore.getCount() - 1, false);
                }
            }
        });

        CheckBoxSelectionModel<ReferenceModel> checkBoxSelectionModel = new CheckBoxSelectionModel<ReferenceModel>();
        checkBoxSelectionModel.setSelectionMode(Style.SelectionMode.MULTI);

        grid = new Grid<ReferenceModel>(referenceStore, createColumnModel(checkBoxSelectionModel.getColumn()));
        grid.setBorders(true);
        grid.setAutoExpandColumn("name");
        grid.setSelectionModel(checkBoxSelectionModel);
        grid.addPlugin(checkBoxSelectionModel);

        contentPanel.add(grid);

        contentPanel.addButton(new Button("Добавить", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(AppEvents.AddReference);
            }
        }));

        contentPanel.addButton(new Button("Удалить", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                List<ReferenceModel> references = grid.getSelectionModel().getSelectedItems();
                if (references.size() > 0) {
                    Dispatcher.forwardEvent(AppEvents.RemoveReference, references);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите справки для Удаления");
                }
            }
        }));

        contentPanel.addButton(new Button("Печатать в PDF", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                List<ReferenceModel> references = grid.getSelectionModel().getSelectedItems();
                if (references.size() > 0) {
                    Dispatcher.forwardEvent(AppEvents.PrintReference, references);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите справки для печати");
                }
            }
        }));

        contentPanel.addButton(new Button("Доложить о готовности", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                List<ReferenceModel> references = grid.getSelectionModel().getSelectedItems();
                if (references.size() > 0) {
                    Dispatcher.forwardEvent(AppEvents.ReadyReference, references);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите готовые справки");
                }
            }
        }));

        contentPanel.addButton(new Button("Выдать", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                List<ReferenceModel> references = grid.getSelectionModel().getSelectedItems();
                if (references.size() > 0) {
                    Dispatcher.forwardEvent(AppEvents.IssueReference, references);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите выданные справки");
                }
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
                return ReferenceModelUtil.statusToString( ((ReferenceModel) model).getState() );
            }
        });

        ColumnConfig issueDateColumnConfig = new ColumnConfig("issueDate", "Выдана", 80);
        issueDateColumnConfig.setDateTimeFormat(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_MEDIUM));

        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

        columns.add(pluginColumn);
        columns.add(nnColumnConfig);
        columns.add(registrationDateColumnConfig);
        columns.add(nameColumnConfig);
        columns.add(groupNameColumnConfig);
        columns.add(typeColumnConfig);
        columns.add(statusColumnConfig);
        columns.add(issueDateColumnConfig);

        return new ColumnModel(columns);
    }

    public void reload() {
        grid.getStore().removeAll();
        grid.getStore().getLoader().load();
    }

//    public void refresh() {
//        grid.getStore().fireEvent(Store.DataChanged, new StoreEvent<ReferenceModel>(grid.getStore()));
//    }

    @Override
    public void show() {
        this.show(false);
    }

    public void show(boolean lastSelect) {
        super.show();
        this.lastSelect = lastSelect;
        reload();
    }

    private enum LoadType {
        ALL("Все"),
        NOT_ISSUED("Не выданные"),
        REGISTERED("Зарегистрированные"),
        PROCESSED("Обрабатываемые"),
        READY("Готовые к выдаче"),
        ISSUED("Выданные");

        private String title;

        LoadType(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}
