package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.FacultyModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;

import java.util.List;
import java.util.Map;

public interface AppServiceAsync {
    void loadFaculty(AsyncCallback<FacultyModel> async);

    void loadMenuData(Long facultyId, AsyncCallback<List<Map<SpecialityModel, List<GroupModel>>>> async);
}
