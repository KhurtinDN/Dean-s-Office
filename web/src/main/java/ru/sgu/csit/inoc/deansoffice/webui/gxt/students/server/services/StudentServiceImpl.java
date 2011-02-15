package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.StudentService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.utils.StudentUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.*;

import java.util.List;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 10:44 PM
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public List<StudentModel> loadStudentList(Long groupId) {
        List<Student> studentList = studentDAO.findByGroupId(groupId);

        return StudentUtil.convertStudentsToStudentModels(studentList);
    }

    @Override
    public List<StudentModel> loadStudentListBySpecialityIdAndCourse(Long specialityId, Integer course) {
        List<Student> studentList = studentDAO.findBySpecialityIdAndCourse(specialityId, course);

        return StudentUtil.convertStudentsToStudentModels(studentList);
    }

    @Override
    public StudentDetailsModel loadStudentDetails(Long studentId) {
        Student student = studentDAO.findById(studentId);

        return StudentUtil.convertStudentToStudentDetailsModel(student);
    }

    @Override
    public void saveStudentDetails(StudentDetailsModel studentDetailsModel) {
        Student student = StudentUtil.convertStudentDetailsModelToStudent(studentDetailsModel);
        studentDAO.update(student);
    }
}
