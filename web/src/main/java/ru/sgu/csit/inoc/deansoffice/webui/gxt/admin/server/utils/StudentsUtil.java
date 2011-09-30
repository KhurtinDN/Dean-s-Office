package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils;

import org.apache.commons.lang.Validate;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils.StudentUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Khurtin
 */
public class StudentsUtil extends StudentUtil {

    public static List<StudentModel> convertStudentsToStudentModels(final List<Student> studentList, final Group group) {
        if (studentList == null) {
            return null;
        }

        final GroupModel groupModel = GroupUtil.convertGroupToGroupModel(group);

        final List<StudentModel> studentModelList = new ArrayList<StudentModel>(studentList.size());

        for (final Student student : studentList) {
            final StudentModel studentModel = convertStudentToStudentModel(student);
            studentModel.setGroup(groupModel);
            studentModelList.add(studentModel);
        }

        return studentModelList;
    }

    public static void populateStudentByStudentModelForSave(final Student student, final StudentModel studentModel) {
        Validate.notNull(student, "student is null");
        Validate.notNull(studentModel, "studentModel is null");

        fillNamesFromPersonModel(student, studentModel);

        student.setStudentIdNumber(studentModel.getStudentIdNumber());
        student.setDivision(convertStudentModelDivisionToStudentDivision(studentModel.getDivision()));
        student.setStudyForm(convertStudentModelStudyFormStudentStudyForm(studentModel.getStudyForm()));
    }
}
