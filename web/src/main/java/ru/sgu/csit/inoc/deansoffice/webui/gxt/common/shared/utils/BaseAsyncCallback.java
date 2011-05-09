package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils;

import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Dispatcher;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.constants.ErrorCode;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.mvc.events.CommonEvents;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/16/11
 * Time: 3:50 PM
 */
public abstract class BaseAsyncCallback<T> implements AsyncCallback<T>  {
    @Override
    public void onFailure(Throwable caught) {
        AppEvent appEvent = new AppEvent(CommonEvents.Error, ErrorCode.ServerReturnError);
        appEvent.setData("throwable", caught);
        Dispatcher.forwardEvent(appEvent);
    }
}
