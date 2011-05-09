package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.controllers.MessageController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.constants.ErrorCode;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/17/11
 * Time: 2:58 PM
 */
public class MessageView extends View {
    private MessageBox infoWithConfirmationMessageBox;

    public MessageView(MessageController controller) {
        super(controller);
    }

    @Override
    protected void initialize() {
        super.initialize();

        infoWithConfirmationMessageBox = new MessageBox();
        infoWithConfirmationMessageBox.setButtons(MessageBox.OK);
        infoWithConfirmationMessageBox.setTitle("Внимание!");
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(CommonEvents.Error)) {
            onError(event);
        } else if (eventType.equals(CommonEvents.Info)) {
            onInfo(event);
        } else if (eventType.equals(CommonEvents.InfoWithConfirmation)) {
            onInfoWithConfirmation(event);
        }
    }

    private void onError(AppEvent event) {
        ErrorCode code = event.getData();

        switch (code) {
            case ServerReturnError:
                Info.display("Важное сообщение", "Ошибка на сервере");
                break;
            case DebugInformation:
                String message = event.getData("message");
                Info.display("DEBUG", message);
                break;
            default:
                Info.display("Ошибка", "Неизвестная ошибка!");
        }
    }

    private void onInfo(AppEvent event) {
        String message = event.getData().toString();
        Info.display("Внимание!", message);
    }

    private void onInfoWithConfirmation(AppEvent event) {
        String message = event.getData().toString();
        infoWithConfirmationMessageBox.setMessage(message);
        infoWithConfirmationMessageBox.show();
    }
}
