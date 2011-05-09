package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.controllers;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.events.StudentEvents;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.mvc.views.ReferenceView;

/**
 * User: hd KhurtinDN (dog) gmail.com
 * Date: 3/26/11
 * Time: 5:26 PM
 */
public class ReferenceController extends Controller {
    private ReferenceView referenceView;

    public ReferenceController() {
        registerEventTypes(StudentEvents.AddReference, StudentEvents.RemoveReference, StudentEvents.UpdateReference);
        registerEventTypes(StudentEvents.ReferenceQueueCall, StudentEvents.RegistrationReference);
        registerEventTypes(StudentEvents.PrintReference, StudentEvents.ReadyReference, StudentEvents.IssueReference);
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
