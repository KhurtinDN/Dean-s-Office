package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.views.MessageView;

/**
 * User: Khurtin Denis ( KhurtinDN (a) gmail.com )
 * Date: 2/17/11
 * Time: 2:57 PM
 */
public class MessageController extends Controller {
    private MessageView messageView;

    public MessageController() {
        registerEventTypes(CommonEvents.Init);
        registerEventTypes(CommonEvents.Info, CommonEvents.InfoWithConfirmation, CommonEvents.Error);
    }

    @Override
    protected void initialize() {
        super.initialize();

        messageView = new MessageView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(messageView, event);
    }
}
