package ru.sgu.csit.inoc.deansoffice.client.gxt.content;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ru.sgu.csit.inoc.deansoffice.shared.dto.StudentDto;

import java.util.List;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 2, 2010
 * Time: 4:57:14 PM
 */
public interface StudentServiceAsync {
    void downloadStudents(Long groupId, AsyncCallback<List<StudentDto>> async);
}
