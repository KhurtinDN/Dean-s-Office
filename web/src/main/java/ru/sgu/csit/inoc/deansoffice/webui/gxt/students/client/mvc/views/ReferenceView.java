package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.orders.ReferenceQueueWindow;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.constants.ErrorCode;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.ReferenceService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;

import java.util.List;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/26/11
 * Time: 5:28 PM
 */
public class ReferenceView extends View {
    private ReferenceQueueWindow referenceQueueWindow;

    public ReferenceView(Controller controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        referenceQueueWindow = new ReferenceQueueWindow();
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(AppEvents.AddReference)) {
            onAddReference(event);
        } else if (eventType.equals(AppEvents.UpdateReference)) {
            onUpdateReference(event);
        } else if (eventType.equals(AppEvents.ReferenceQueueCall)) {
            onReferenceQueueCall(event);
        } else if (eventType.equals(AppEvents.RegistrationReference)) {
            onRegistrationReference(event);
        } else if (eventType.equals(AppEvents.PrintReference)) {
            onPrintReference(event);
        } else if (eventType.equals(AppEvents.ReadyReference)) {
            onReadyReference(event);
        } else if (eventType.equals(AppEvents.IssueReference)) {
            onIssueReference(event);
        }
    }

    private void onAddReference(AppEvent event) {
        // todo
    }

    private void onUpdateReference(AppEvent event) {
        ReferenceModel referenceModel = event.getData();

        ReferenceService.App.getInstance().updateReference(referenceModel, new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                appEvent.setData("throwable", caught);
                Dispatcher.forwardEvent(appEvent);
            }

            @Override
            public void onSuccess(Boolean result) {
                String message = result ? "Справка успешно изменена!" : "Произошла ошибка при изменении справки!";
                Dispatcher.forwardEvent(AppEvents.Info, message);
                ReferenceView.this.referenceQueueWindow.reload();
            }
        });
    }

    private void onReferenceQueueCall(AppEvent event) {
        referenceQueueWindow.show();
    }

    private void onRegistrationReference(AppEvent event) {
        ReferenceModel referenceModel = event.getData();

        ReferenceService.App.getInstance().registrationReference(referenceModel, new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                appEvent.setData("throwable", caught);
                Dispatcher.forwardEvent(appEvent);
            }

            @Override
            public void onSuccess(Boolean result) {
                String message = result ? "Справка успешно добавлена!" : "Произошла ошибка при добавлении справки!";
                Dispatcher.forwardEvent(AppEvents.Info, message);
                ReferenceView.this.referenceQueueWindow.reload();
            }
        });
    }

    private void onPrintReference(AppEvent event) {
        List<ReferenceModel> referenceModelList = event.getData();

        ReferenceService.App.getInstance().printReferences(referenceModelList, new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                appEvent.setData("throwable", caught);
                Dispatcher.forwardEvent(appEvent);
            }

            @Override
            public void onSuccess(Boolean result) {
                String message = result ? "Запрос на печать прошел успешно!" : "Произошла ошибка!";
                Dispatcher.forwardEvent(AppEvents.Info, message);
                ReferenceView.this.referenceQueueWindow.reload();
            }
        });
    }

    private void onReadyReference(AppEvent event) {
        final List<ReferenceModel> referenceModelList = event.getData();

        ReferenceService.App.getInstance().readyReferences(referenceModelList, new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                appEvent.setData("throwable", caught);
                Dispatcher.forwardEvent(appEvent);
            }

            @Override
            public void onSuccess(Boolean result) {
                String message = result ? "Справки приведены в состояние готовности!" : "Произошла ошибка!";
                Dispatcher.forwardEvent(AppEvents.Info, message);
                ReferenceView.this.referenceQueueWindow.reload();

                StringBuilder stringBuilder = new StringBuilder();
                for (ReferenceModel referenceModel : referenceModelList) {
                    stringBuilder.append(referenceModel.getId()).append(",");
                }

                String url = "../documents/references.pdf?referencesId=" + stringBuilder.toString();
                Window.open(url, "_blank", "");
            }
        });
    }

    private void onIssueReference(AppEvent event) {
        List<ReferenceModel> referenceModelList = event.getData();

        ReferenceService.App.getInstance().issueReferences(referenceModelList, new AsyncCallback<Boolean>() {
            @Override
            public void onFailure(Throwable caught) {
                AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                appEvent.setData("throwable", caught);
                Dispatcher.forwardEvent(appEvent);
            }

            @Override
            public void onSuccess(Boolean result) {
                String message = result ? "Справки успешно выданы!" : "Произошла ошибка при выдачи справок!";
                Dispatcher.forwardEvent(AppEvents.Info, message);
                ReferenceView.this.referenceQueueWindow.reload();
            }
        });
    }
}
