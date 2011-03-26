package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.AppEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views.ReferenceView;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/26/11
 * Time: 5:26 PM
 */
public class ReferenceController extends Controller {
    private ReferenceView referenceView;

    public ReferenceController() {
        registerEventTypes(AppEvents.AddReference, AppEvents.UpdateReference, AppEvents.ReferenceQueueCall);
        registerEventTypes(AppEvents.PrintReference, AppEvents.ReadyReference, AppEvents.IssueReference);
    }

    @Override
    protected void initialize() {
        super.initialize();

        referenceView = new ReferenceView(this);
    }

    @Override
    public void handleEvent(AppEvent event) {
        forwardToView(referenceView, event);
    }
}
