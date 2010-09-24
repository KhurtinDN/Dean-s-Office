package ru.sgu.csit.inoc.deansoffice.client.gxt.menu;

import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Map;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 16, 2010
 * Time: 10:51:39 PM
 */
public interface MenuServiceAsync {
    void downloadSpecialityName(AsyncCallback<Map<Long, String>> asyncCallback);

    void downloadGroupName(Long specialityId, AsyncCallback<Map<Long, String>> async);
}
