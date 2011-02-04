package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.extjs.gxt.ui.client.data.BaseModel;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;

import java.util.List;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 10:44 PM
 */
@RemoteServiceRelativePath("GWTServices/StudentService")
public interface StudentService extends RemoteService {
    Integer getCourseCount();
    List<SpecialityModel> loadSpecialityList();
    List<GroupModel> loadGroupList(Integer course, Long specialityId);
    List<StudentModel> loadStudentList(Long groupId);
    List<BaseModel> loadNavigationTree(Integer course, BaseModel parent);

    List<StudentModel> loadStudentListBySpecialityIdAndCourse(Long specialityId, Integer course);

    public static class App {
        private static final StudentServiceAsync ourInstance = GWT.create(StudentService.class);

        public static StudentServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
