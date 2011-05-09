package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentDetailsModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;

import java.util.List;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 10:44 PM
 */
@RemoteServiceRelativePath("GWTServices/StudentService")
public interface StudentService extends RemoteService {

    List<StudentModel> loadStudentList(Long groupId);

    List<StudentModel> loadStudentListBySpecialityIdAndCourse(Long specialityId, Integer course);

    StudentDetailsModel loadStudentDetails(Long studentId);

    void saveStudentDetails(StudentDetailsModel studentDetailsModel);

    public static class Util {
        private static final StudentServiceAsync ourInstance = GWT.create(StudentService.class);

        public static StudentServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
