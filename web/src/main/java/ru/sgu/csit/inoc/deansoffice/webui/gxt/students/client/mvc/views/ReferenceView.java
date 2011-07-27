package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.extjs.gxt.ui.client.mvc.View;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.ReferenceModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.BaseAsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.references.AddReferenceDialog;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.components.references.ReferenceQueueWindow;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.ReferenceService;

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

        if (eventType.equals(StudentEvents.AddReference)) {
            onAddReference();
        } else if (eventType.equals(StudentEvents.RemoveReference)) {
            onRemoveReference(event);
        } else if (eventType.equals(StudentEvents.UpdateReference)) {
            onUpdateReference(event);
        } else if (eventType.equals(StudentEvents.ReferenceQueueCall)) {
            onReferenceQueueCall(event);
        } else if (eventType.equals(StudentEvents.RegistrationReference)) {
            onRegistrationReference(event);
        } else if (eventType.equals(StudentEvents.PrintReference)) {
            onPrintReference(event);
        } else if (eventType.equals(StudentEvents.ReadyReference)) {
            onReadyReference(event);
        } else if (eventType.equals(StudentEvents.IssueReference)) {
            onIssueReference(event);
        }
    }

    private void onAddReference() {
        new AddReferenceDialog(referenceQueueWindow).show();
    }

    private void onRemoveReference(AppEvent event) {
        final List<ReferenceModel> referenceModelList = event.getData();

        List<Long> referenceIdList = new ArrayList<Long>(referenceModelList.size());
        for (ReferenceModel referenceModel : referenceModelList) {
            referenceIdList.add(referenceModel.getId());
        }

        ReferenceService.Util.getInstance().removeReferences(referenceIdList, new BaseAsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                referenceQueueWindow.reload();
                Dispatcher.forwardEvent(CommonEvents.Info, "Справки удалены успешно!");
            }
        });
    }

    private void onUpdateReference(AppEvent event) {
        ReferenceModel referenceModel = event.getData();

        ReferenceService.Util.getInstance().updateDestinationReference(referenceModel.getId(),
                referenceModel.getDestination(), new BaseAsyncCallback<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        Dispatcher.forwardEvent(CommonEvents.Info, "Справка изменена успешно!");
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

        ReferenceService.Util.getInstance().registrationReference(type, ownerId, new BaseAsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                Dispatcher.forwardEvent(CommonEvents.Info, "Справка добавлена успешно!");
            }
        });
    }

    private void onPrintReference(AppEvent event) {
        final List<ReferenceModel> referenceModelList = event.getData();

        List<Long> referenceIdList = new ArrayList<Long>(referenceModelList.size());
        for (ReferenceModel referenceModel : referenceModelList) {
            referenceIdList.add(referenceModel.getId());
        }

        StringBuilder urlBuilder = new StringBuilder(GWT.getHostPageBaseURL());
        urlBuilder.append("documents/references.pdf?referencesId=");

        for (Long referenceId : referenceIdList) {
            urlBuilder.append(referenceId).append(",");
        }

        Window.open(urlBuilder.toString(), "_blank", "");

        ReferenceService.Util.getInstance().printReferences(referenceIdList, new BaseAsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                referenceQueueWindow.reload();
                Dispatcher.forwardEvent(CommonEvents.Info, "Запрос на печать прошел успешно!");
            }
        });
    }

    private void onReadyReference(AppEvent event) {
        final List<ReferenceModel> referenceModelList = event.getData();

        List<Long> referenceIdList = new ArrayList<Long>(referenceModelList.size());
        for (ReferenceModel referenceModel : referenceModelList) {
            referenceIdList.add(referenceModel.getId());
        }

        ReferenceService.Util.getInstance().readyReferences(referenceIdList, new BaseAsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                referenceQueueWindow.reload();
                Dispatcher.forwardEvent(CommonEvents.Info, "Справки подготовлены успешно!");
            }
        });
    }

    private void onIssueReference(AppEvent event) {
        final List<ReferenceModel> referenceModelList = event.getData();

        List<Long> referenceIdList = new ArrayList<Long>(referenceModelList.size());
        for (ReferenceModel referenceModel : referenceModelList) {
            referenceIdList.add(referenceModel.getId());
        }

        ReferenceService.Util.getInstance().issueReferences(referenceIdList, new BaseAsyncCallback<Void>() {
            @Override
            public void onSuccess(Void result) {
                referenceQueueWindow.reload();
                Dispatcher.forwardEvent(CommonEvents.Info, "Справки выданы успешно!");
            }
        });
    }
}
