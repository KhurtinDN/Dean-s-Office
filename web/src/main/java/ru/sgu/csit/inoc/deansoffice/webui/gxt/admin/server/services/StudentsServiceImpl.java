package ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.services;

import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.client.services.StudentsService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.admin.server.utils.StudentsUtil;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model.StudentModel;

import java.util.List;

/**
 * @author Denis Khurtin
 */
@Service("AdminStudentsService")
public class StudentsServiceImpl implements StudentsService {

    @Autowired
    private StudentDAO studentDAO;
    @Autowired
    private SpecialityDAO specialityDAO;
    @Autowired
    private GroupDAO groupDAO;

    @Override
    public List<StudentModel> loadStudentList(Long groupId) {
        Validate.notNull(groupId, "groupId is null");

        final Group group = groupDAO.findById(groupId);
        List<Student> studentList = studentDAO.findByGroupId(groupId);

        return StudentsUtil.convertStudentsToStudentModels(studentList, group);
    }

    @Override
    public StudentModel saveOrUpdate(final StudentModel studentModel) {
        Validate.notNull(studentModel, "studentModel is null");

        final Student student = new Student();

        StudentsUtil.populateStudentByStudentModel(student, studentModel);

        List<Speciality> specialities = specialityDAO.findByShortName(studentModel.getSpecialityName()); // todo: need find by id
        if (specialities == null || specialities.size() != 1) {
            throw new RuntimeException("Specialty not specified.");
        }
        student.setSpeciality(specialities.get(0));

        List<Group> groups = groupDAO.findByName(studentModel.getGroupName());  // todo: need find by id
        if (groups == null || groups.size() != 1) {
            throw new RuntimeException("Group not specified.");
        }
        student.setGroup(groups.get(0));

        studentDAO.saveOrUpdate(student);

        return StudentsUtil.convertStudentToStudentModel(student);
    }

    @Override
    public void delete(final List<Long> studentIdList) {
        Validate.noNullElements(studentIdList, "EmployeeId list is null or contains null element.");

        for (final Long id : studentIdList) {
            studentDAO.deleteById(id);
        }
    }
}
