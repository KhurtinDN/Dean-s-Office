package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Html;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.OrderService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.OrderModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 3/9/11
 * Time: 1:15 PM
 */
public class OrderQueueWindow extends Window {
    private ListLoader<ListLoadResult<OrderModel>> loader;

    public OrderQueueWindow() {
        setHeading("Очередь приказов");
        setSize(800, 600);
        setModal(true);
        setBlinkModal(true);
        setLayout(new BorderLayout());

        BorderLayoutData centerLayoutData = new BorderLayoutData(Style.LayoutRegion.CENTER);

        BorderLayoutData eastLayoutData = new BorderLayoutData(Style.LayoutRegion.EAST, 300);
        eastLayoutData.setCollapsible(true);
        eastLayoutData.setSplit(true);

        add(createMainContentPanel(), centerLayoutData);
        add(createInfoContentPanel(), eastLayoutData);
    }

    private ContentPanel createMainContentPanel() {
        ContentPanel contentPanel = new ContentPanel(new FitLayout());
        contentPanel.setHeading("Приказы");

        RpcProxy<List<OrderModel>> proxy = new RpcProxy<List<OrderModel>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<List<OrderModel>> listAsyncCallback) {
                OrderService.App.getInstance().loadOrders(listAsyncCallback);
            }
        };
        loader = new BaseListLoader<ListLoadResult<OrderModel>>(proxy);
        ListStore<OrderModel> orderStore = new ListStore<OrderModel>(loader);

        Grid<OrderModel> grid = new Grid<OrderModel>(orderStore, createColumnModel());
        grid.setBorders(true);
        grid.setAutoExpandColumn("name");

        contentPanel.add(grid);

        contentPanel.addButton(new Button("Добавить новый", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                // todo: add new Order
            }
        }));

        contentPanel.addButton(new Button("Изменить выбранный", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                // todo: add new Order
            }
        }));

        return contentPanel;
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

        ColumnConfig typeColumnConfig = new ColumnConfig("type", "Тип", 100);
        typeColumnConfig.setSortable(false);

        ColumnConfig nameColumnConfig = new ColumnConfig("name", "Название", 200);
        nameColumnConfig.setSortable(false);

        ColumnConfig statusColumnConfig = new ColumnConfig("status", "Статус", 100);
        statusColumnConfig.setSortable(false);

        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

        columns.add(nnColumnConfig);
        columns.add(typeColumnConfig);
        columns.add(nameColumnConfig);
        columns.add(statusColumnConfig);

        return new ColumnModel(columns);
    }

    private ContentPanel createInfoContentPanel() {
        ContentPanel contentPanel = new ContentPanel();
        contentPanel.setHeading("Информация о приказе");

        contentPanel.add(new Html("Приказ № 23"));

        return contentPanel;
    }

    @Override
    public void show() {
        super.show();

        loader.load();
    }
}
