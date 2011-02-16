package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.AdditionalStudentDataDAO;
import ru.sgu.csit.inoc.deansoffice.dao.ParentDAO;
import ru.sgu.csit.inoc.deansoffice.dao.PassportDAO;
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
    @Autowired
    private PassportDAO passportDAO;
    @Autowired
    private AdditionalStudentDataDAO additionalStudentDataDAO;
    @Autowired
    private ParentDAO parentDAO;

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
        Student student = studentDAO.findById(studentDetailsModel.getId());
        StudentUtil.populateStudentByStudentDetailsModel(student, studentDetailsModel);

        if (student.getAdditionalData() != null) {
            Student.AdditionalStudentData additionalStudentData = student.getAdditionalData();
            if (additionalStudentData.getPassports() != null) {
                for (Passport passport : additionalStudentData.getPassports()) {
                    passportDAO.saveOrUpdate(passport);
                }
            }
            if (additionalStudentData.getFather() != null) {
                parentDAO.saveOrUpdate(additionalStudentData.getFather());
            }
            if (additionalStudentData.getMother() != null) {
                parentDAO.saveOrUpdate(additionalStudentData.getMother());
            }

            additionalStudentDataDAO.saveOrUpdate(additionalStudentData);
        }

        studentDAO.update(student);
    }
}
