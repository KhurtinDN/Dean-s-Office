package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;

import java.util.List;

/**
 * @author Denis Khurtin
 */
@RemoteServiceRelativePath("GWTServices/AdminStudentsService")
public interface StudentsService extends RemoteService {

    List<StudentModel> loadStudentList(Long groupId);

    StudentModel saveOrUpdate(StudentModel studentModel);
    void delete(List<Long> studentIdList);

    public static class Util {
        private static final StudentsServiceAsync ourInstance = GWT.create(StudentsService.class);

        public static StudentsServiceAsync getInstance() {
            return ourInstance;
        }
    }
}

