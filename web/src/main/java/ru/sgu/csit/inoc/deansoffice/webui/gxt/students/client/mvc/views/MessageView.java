package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.View;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers.MessageController;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;

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
        infoWithConfirmationMessageBox.setTitle("Внимание! Важное сообщение");
    }

    @Override
    protected void handleEvent(AppEvent event) {
        EventType eventType = event.getType();

        if (eventType.equals(AppEvents.Info)) {
            onInfo(event);
        } else if (eventType.equals(AppEvents.InfoWithConfirmation)) {
            onInfoWithConfirmation(event);
        }
    }

    private void onInfo(AppEvent event) {
        String message = event.getData().toString();
        Info.display("Внимание! Важное сообщение", message);
    }

    private void onInfoWithConfirmation(AppEvent event) {
        String message = event.getData().toString();
        infoWithConfirmationMessageBox.setMessage(message);
        infoWithConfirmationMessageBox.show();
    }
}
