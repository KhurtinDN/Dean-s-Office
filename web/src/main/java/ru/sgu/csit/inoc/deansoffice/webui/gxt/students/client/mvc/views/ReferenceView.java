package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.references.AddReferenceDialog;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.references.ReferenceQueueWindow;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.constants.ErrorCode;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.ReferenceService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.ReferenceModel;

import java.util.ArrayList;
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
        } else if (eventType.equals(AppEvents.RemoveReference)) {
            onRemoveReference(event);
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
        new AddReferenceDialog(referenceQueueWindow).show();
    }

    private void onRemoveReference(AppEvent event) {
        final List<ReferenceModel> referenceModelList = event.getData();

        List<Long> referenceIdList = new ArrayList<Long>(referenceModelList.size());
        for (ReferenceModel referenceModel : referenceModelList) {
            referenceIdList.add(referenceModel.getId());
        }

        ReferenceService.App.getInstance().removeReferences(referenceIdList, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                appEvent.setData("throwable", caught);
                Dispatcher.forwardEvent(appEvent);
            }

            @Override
            public void onSuccess(Void result) {
                referenceQueueWindow.reload();
                Dispatcher.forwardEvent(AppEvents.Info, "Справки удалены успешно!");
            }
        });
    }

    private void onUpdateReference(AppEvent event) {
        ReferenceModel referenceModel = event.getData();

        ReferenceService.App.getInstance().updateDestinationReference(referenceModel.getId(),
                referenceModel.getDestination(), new AsyncCallback<Void>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                        appEvent.setData("throwable", caught);
                        Dispatcher.forwardEvent(appEvent);
                    }

                    @Override
                    public void onSuccess(Void result) {
                        Dispatcher.forwardEvent(AppEvents.Info, "Справка изменена успешно!");
                    }
                });
    }

    private void onReferenceQueueCall(AppEvent event) {
        Boolean lastSelect = event.getData();
        referenceQueueWindow.show(lastSelect != null && lastSelect);
    }

    private void onRegistrationReference(AppEvent event) {
        ReferenceModel.ReferenceType type = event.getData("referenceType");
        Long ownerId = event.getData("ownerId");

        ReferenceService.App.getInstance().registrationReference(type, ownerId, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                appEvent.setData("throwable", caught);
                Dispatcher.forwardEvent(appEvent);
            }

            @Override
            public void onSuccess(Void result) {
                Dispatcher.forwardEvent(AppEvents.Info, "Справка добавлена успешно!");
            }
        });
    }

    private void onPrintReference(AppEvent event) {
        final List<ReferenceModel> referenceModelList = event.getData();

        List<Long> referenceIdList = new ArrayList<Long>(referenceModelList.size());
        for (ReferenceModel referenceModel : referenceModelList) {
            referenceIdList.add(referenceModel.getId());
        }

        StringBuilder stringBuilder = new StringBuilder();
        for (Long referenceId : referenceIdList) {
            stringBuilder.append(referenceId).append(",");
        }

        Window.open("../documents/references.pdf?referencesId=" + stringBuilder.toString(), "_blank", "");

        ReferenceService.App.getInstance().printReferences(referenceIdList, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                appEvent.setData("throwable", caught);
                Dispatcher.forwardEvent(appEvent);
            }

            @Override
            public void onSuccess(Void result) {
                referenceQueueWindow.reload();
                Dispatcher.forwardEvent(AppEvents.Info, "Запрос на печать прошел успешно!");
            }
        });
    }

    private void onReadyReference(AppEvent event) {
        final List<ReferenceModel> referenceModelList = event.getData();

        List<Long> referenceIdList = new ArrayList<Long>(referenceModelList.size());
        for (ReferenceModel referenceModel : referenceModelList) {
            referenceIdList.add(referenceModel.getId());
        }

        ReferenceService.App.getInstance().readyReferences(referenceIdList, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                appEvent.setData("throwable", caught);
                Dispatcher.forwardEvent(appEvent);
            }

            @Override
            public void onSuccess(Void result) {
                referenceQueueWindow.reload();
                Dispatcher.forwardEvent(AppEvents.Info, "Справки подготовлены успешно!");
            }
        });
    }

    private void onIssueReference(AppEvent event) {
        final List<ReferenceModel> referenceModelList = event.getData();

        List<Long> referenceIdList = new ArrayList<Long>(referenceModelList.size());
        for (ReferenceModel referenceModel : referenceModelList) {
            referenceIdList.add(referenceModel.getId());
        }

        ReferenceService.App.getInstance().issueReferences(referenceIdList, new AsyncCallback<Void>() {
            @Override
            public void onFailure(Throwable caught) {
                AppEvent appEvent = new AppEvent(AppEvents.Error, ErrorCode.ServerReturnError);
                appEvent.setData("throwable", caught);
                Dispatcher.forwardEvent(appEvent);
            }

            @Override
            public void onSuccess(Void result) {
                referenceQueueWindow.reload();
                Dispatcher.forwardEvent(AppEvents.Info, "Справки выданы успешно!");
            }
        });
    }
}
