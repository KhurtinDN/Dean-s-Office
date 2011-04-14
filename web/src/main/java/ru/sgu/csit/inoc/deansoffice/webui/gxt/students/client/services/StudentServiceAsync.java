package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentDetailsModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;

import java.util.List;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 10:44 PM
 */
public interface StudentServiceAsync {

    void loadStudentList(Long groupId, AsyncCallback<List<StudentModel>> async);

    void loadStudentListBySpecialityIdAndCourse(Long specialityId, Integer course,
                                                AsyncCallback<List<StudentModel>> asyncCallback);

    void loadStudentDetails(Long studentId, AsyncCallback<StudentDetailsModel> async);

    void saveStudentDetails(StudentDetailsModel studentDetailsModel, AsyncCallback<Void> async);
}
