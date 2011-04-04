package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.references;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.IconHelper;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.MessageBox;
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
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
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
    private Grid<ReferenceModel> grid;
    private SimpleComboBox<LoadType> loadTypeComboBox = createLoadTypeComboBox();
    private ContentPanel mainContentPanel = createMainContentPanel();
    private ReferenceInfoPanel referenceInfoPanel = new ReferenceInfoPanel();

    private boolean lastSelect = false;

    public ReferenceQueueWindow() {
        setHeading("Очередь справок");
        setSize(1200, 600);
        setMaximizable(true);
        setModal(true);
        setBlinkModal(true);
    }

    private SimpleComboBox<LoadType> createLoadTypeComboBox() {
        SimpleComboBox<LoadType> loadTypeSimpleComboBox = new SimpleComboBox<LoadType>();
        loadTypeSimpleComboBox.setEditable(false);
        loadTypeSimpleComboBox.setTriggerAction(ComboBox.TriggerAction.ALL);

        loadTypeSimpleComboBox.add(ReferenceQueueWindow.LoadType.ALL);
        loadTypeSimpleComboBox.add(ReferenceQueueWindow.LoadType.NOT_ISSUED);
        loadTypeSimpleComboBox.add(ReferenceQueueWindow.LoadType.REGISTERED);
        loadTypeSimpleComboBox.add(ReferenceQueueWindow.LoadType.PROCESSED);
        loadTypeSimpleComboBox.add(ReferenceQueueWindow.LoadType.READY);
        loadTypeSimpleComboBox.add(ReferenceQueueWindow.LoadType.ISSUED);

        loadTypeSimpleComboBox.setSimpleValue(ReferenceQueueWindow.LoadType.NOT_ISSUED);

        loadTypeSimpleComboBox.addSelectionChangedListener(new SelectionChangedListener<SimpleComboValue<LoadType>>() {
            @Override
            public void selectionChanged(SelectionChangedEvent<SimpleComboValue<LoadType>> se) {
                reload();
            }
        });

        return loadTypeSimpleComboBox;
    }

    private ToolBar createToolBar() {
        Button addButton = new Button("Добавить", IconHelper.createStyle("addButton-icon"));
        addButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Dispatcher.forwardEvent(AppEvents.AddReference);
            }
        });

        Button removeButton = new Button("Удалить", IconHelper.createStyle("removeButton-icon"));
        removeButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                final List<ReferenceModel> references = grid.getSelectionModel().getSelectedItems();
                if (references.size() > 0) {
                    MessageBox.confirm("Удаление справок", "Вы действително хотите удалить выбранные справки?",
                            new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {
                                    if (be.getDialog().yesText.equals(be.getButtonClicked().getText())) {
                                        Dispatcher.forwardEvent(AppEvents.RemoveReference, references);
                                    }
                                }
                            });
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите справки для удаления");
                }
            }
        });

        Button pdfButton = new Button("Печать в PDF", IconHelper.createStyle("pdfButton-icon"));
        pdfButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                List<ReferenceModel> references = grid.getSelectionModel().getSelectedItems();
                if (references.size() > 0) {
                    Dispatcher.forwardEvent(AppEvents.PrintReference, references);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите справки для печати");
                }
            }
        });

        Button readyButton = new Button("Доложить о готовности", IconHelper.createStyle("readyButton-icon"));
        readyButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                List<ReferenceModel> references = grid.getSelectionModel().getSelectedItems();
                if (references.size() > 0) {
                    Dispatcher.forwardEvent(AppEvents.ReadyReference, references);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите готовые справки");
                }
            }
        });

        Button issueButton = new Button("Выдать", IconHelper.createStyle("issueButton-icon"));
        issueButton.addSelectionListener(new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                List<ReferenceModel> references = grid.getSelectionModel().getSelectedItems();
                if (references.size() > 0) {
                    Dispatcher.forwardEvent(AppEvents.IssueReference, references);
                } else {
                    Dispatcher.forwardEvent(AppEvents.InfoWithConfirmation, "Выберите выданные справки");
                }
            }
        });

        ToolBar toolBar = new ToolBar();

        toolBar.add(addButton);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(removeButton);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(pdfButton);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(readyButton);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(issueButton);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(new FillToolItem());
        toolBar.add(loadTypeComboBox);

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

        mainContentPanel.setTopComponent(createToolBar());

        add(mainContentPanel, centerLayoutData);
        add(referenceInfoPanel, eastLayoutData);
    }

    private ContentPanel createMainContentPanel() {
        ContentPanel contentPanel = new ContentPanel(new FitLayout());
        contentPanel.setHeading("Справки");

        RpcProxy<PagingLoadResult<ReferenceModel>> proxy = new RpcProxy<PagingLoadResult<ReferenceModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<ReferenceModel>> asyncCallback) {
                PagingLoadConfig pagingLoadConfig = (PagingLoadConfig) loadConfig;

                switch (loadTypeComboBox.getSimpleValue()) {
                    case ALL:
                        ReferenceService.App.getInstance().loadAllReferences(pagingLoadConfig, asyncCallback);
                        break;
                    case NOT_ISSUED:
                        ReferenceService.App.getInstance().loadNotIssuedReferences(pagingLoadConfig, asyncCallback);
                        break;
                    case REGISTERED:
                        ReferenceService.App.getInstance().loadRegisteredReferences(pagingLoadConfig, asyncCallback);
                        break;
                    case PROCESSED:
                        ReferenceService.App.getInstance().loadProcessedReferences(pagingLoadConfig, asyncCallback);
                        break;
                    case READY:
                        ReferenceService.App.getInstance().loadReadyReferences(pagingLoadConfig, asyncCallback);
                        break;
                    case ISSUED:
                        ReferenceService.App.getInstance().loadIssuedReferences(pagingLoadConfig, asyncCallback);
                        break;
                }
            }
        };

        PagingLoader<PagingLoadResult<ReferenceModel>> pagingLoader =
                new BasePagingLoader<PagingLoadResult<ReferenceModel>>(proxy);
        pagingLoader.setRemoteSort(true);

        ListStore<ReferenceModel> referenceStore = new ListStore<ReferenceModel>(pagingLoader);
        referenceStore.sort("registrationDate", Style.SortDir.ASC);

        pagingLoader.addLoadListener(new LoadListener() {
            @Override
            public void loaderLoad(LoadEvent le) {
                if (lastSelect && grid.getStore().getCount() > 0) {
                    lastSelect = false;

                    grid.getSelectionModel().select(grid.getStore().getCount() - 1, false);
                }
            }
        });

        CheckBoxSelectionModel<ReferenceModel> checkBoxSelectionModel = new CheckBoxSelectionModel<ReferenceModel>();
        checkBoxSelectionModel.setSelectionMode(Style.SelectionMode.MULTI);

        grid = new Grid<ReferenceModel>(referenceStore, createColumnModel(checkBoxSelectionModel.getColumn()));
        grid.setBorders(true);
        grid.setLoadMask(true);
        grid.setAutoExpandColumn("fullName");
        grid.setSelectionModel(checkBoxSelectionModel);
        grid.addPlugin(checkBoxSelectionModel);

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

        contentPanel.add(grid);

        PagingToolBar pagingToolBar = new PagingToolBar(20);
//        pagingToolBar.setAlignment(Style.HorizontalAlignment.CENTER);
        pagingToolBar.bind(pagingLoader);

        contentPanel.setBottomComponent(pagingToolBar);

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

        ColumnConfig nameColumnConfig = new ColumnConfig("fullName", "ФИО", 200);
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
                return ReferenceModelUtil.typeToString(((ReferenceModel) model).getType());
            }
        });

        ColumnConfig groupNameColumnConfig = new ColumnConfig("groupName", "Группа", 80);
        groupNameColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config, int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return ((ReferenceModel) model).getStudent().getGroupName();
            }
        });

        ColumnConfig statusColumnConfig = new ColumnConfig("state", "Состояние", 110);
        statusColumnConfig.setRenderer(new GridCellRenderer() {
            @Override
            public Object render(ModelData model, String property, ColumnData config,
                                 int rowIndex, int colIndex, ListStore listStore, Grid grid) {
                return ReferenceModelUtil.statusToString(((ReferenceModel) model).getState());
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

        if (lastSelect) {
            loadTypeComboBox.setSimpleValue(LoadType.NOT_ISSUED);
        } else {
            reload();
        }

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
