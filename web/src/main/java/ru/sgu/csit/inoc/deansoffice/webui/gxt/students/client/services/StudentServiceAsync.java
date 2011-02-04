package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;

import java.util.List;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 10:44 PM
 */
public interface StudentServiceAsync {
    void getCourseCount(AsyncCallback<Integer> asyncCallback);
    void loadSpecialityList(AsyncCallback<List<SpecialityModel>> async);
    void loadGroupList(Integer course, Long specialityId, AsyncCallback<List<GroupModel>> listAsyncCallback);
    void loadStudentList(Long groupId, AsyncCallback<List<StudentModel>> async);
    void loadNavigationTree(Integer course, BaseModel parent, AsyncCallback<List<BaseModel>> async);

    void loadStudentListBySpecialityIdAndCourse(Long specialityId, Integer course,
                                                AsyncCallback<List<StudentModel>> asyncCallback);
}
