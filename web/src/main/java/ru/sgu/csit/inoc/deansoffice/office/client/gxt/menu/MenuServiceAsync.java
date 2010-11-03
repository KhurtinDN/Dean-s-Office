package ru.sgu.csit.inoc.deansoffice.office.client.gxt.menu;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.GroupDto;
import ru.sgu.csit.inoc.deansoffice.office.shared.dto.SpecialityDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 16, 2010
 * Time: 10:51:39 PM
 */
public interface MenuServiceAsync {
    void downloadMenuData(AsyncCallback<ArrayList<Map<SpecialityDto, List<GroupDto>>>> async);
}
