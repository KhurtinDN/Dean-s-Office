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

    public static List<StudentModel> convertStudentsToStudentModels(List<Student> studentList, Group group) {
        if (studentList == null) {
            return null;
        }

        final GroupModel groupModel = GroupUtil.convertGroupToGroupModel(group);

        final List<StudentModel> studentModelList = new ArrayList<StudentModel>(studentList.size());

        for (Student student : studentList) {
            final StudentModel studentModel = convertStudentToStudentModel(student);
            studentModel.setGroup(groupModel);
            studentModelList.add(studentModel);
        }

        return studentModelList;
    }

    public static void populateStudentByStudentModel(Student student, StudentModel studentModel) {
        Validate.notNull(student, "student is null");
        Validate.notNull(studentModel, "studentModel is null");

        populatePersonByPersonModel(student, studentModel);

        if (student.getAdditionalData() == null) {
            Student.AdditionalStudentData additionalStudentData = new Student.AdditionalStudentData();
            student.setAdditionalData(additionalStudentData);
        }

        student.setStudentIdNumber(studentModel.getStudentIdNumber());
        student.setDivision(convertStudentModelDivisionToStudentDivision(studentModel.getDivision()));
        student.setStudyForm(convertStudentModelStudyFormStudentStudyForm(studentModel.getStudyForm()));
    }
}
