package ru.sgu.csit.inoc.deansoffice.client.gxt.content;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.google.gwt.core.client.GWT;
import ru.sgu.csit.inoc.deansoffice.shared.dto.StudentDto;

import java.util.List;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 2, 2010
 * Time: 4:57:14 PM
 */
@RemoteServiceRelativePath("GWTServices/studentService")
public interface StudentService extends RemoteService {
    List<StudentDto> downloadStudents(Long groupId);
    /**
     * Utility/Convenience class.
     * Use StudentService.App.getInstance() to access static instance of StudentServiceAsync
     */
    public static class App {
        private static final StudentServiceAsync ourInstance = (StudentServiceAsync) GWT.create(StudentService.class);

        public static StudentServiceAsync getInstance() {
            return ourInstance;
        }
    }
}
