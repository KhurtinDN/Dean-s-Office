package ru.sgu.csit.inoc.deansoffice.webui.gxt.students.server.services;

import com.extjs.gxt.ui.client.data.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.dao.FacultyDAO;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.GroupModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.SpecialityModel;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.client.services.StudentService;
import ru.sgu.csit.inoc.deansoffice.webui.gxt.students.shared.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 1/26/11
 * Time: 10:44 PM
 */
@Service("StudentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private FacultyDAO facultyDAO;

    @Autowired
    private SpecialityDAO specialityDAO;

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private StudentDAO studentDAO;

    private Faculty getCurrentFaculty() {
        List<Faculty> facultyList = facultyDAO.findAll();     // todo: how to get strict ONE object

        if (facultyList == null || facultyList.size() != 1) {
            throw new RuntimeException("Faculty must be one object!"); // todo: how get csit?
        }

        return facultyList.get(0);
    }

    @Override
    public Integer getCourseCount() {
        Faculty faculty = getCurrentFaculty();
        return faculty.getCourseCount();
    }

    @Override
    public List<SpecialityModel> loadSpecialityList() {
        Faculty faculty = getCurrentFaculty();

        List<Speciality> specialityList = specialityDAO.findByFaculty(faculty);
        List<SpecialityModel> specialityModelList = new ArrayList<SpecialityModel>(specialityList.size());

        for (Speciality speciality : specialityList) {
            specialityModelList.add(new SpecialityModel(speciality.getId(), speciality.getShortName()));
        }

        return specialityModelList;
    }

    @Override
    public List<GroupModel> loadGroupList(Integer course, Long specialityId) {
        List<Group> groupList = groupDAO.findByCourseAndSpecialityId(course, specialityId);
        List<GroupModel> groupModelList = new ArrayList<GroupModel>(groupList.size());

        for (Group group : groupList) {
            groupModelList.add(new GroupModel(group.getId(), group.getName()));
        }

        return groupModelList;
    }

    @Override
    public List<StudentModel> loadStudentList(Long groupId) {
        List<Student> studentList = studentDAO.findByGroupId(groupId);

        return convertStudentsToStudentModels(studentList);
    }

    @Override
    public List<BaseModel> loadNavigationTree(Integer course, BaseModel parent) {
        List<BaseModel> resultList = null;
        if (parent == null) {
            List<SpecialityModel> specialityModelList = loadSpecialityList();
            resultList = new ArrayList<BaseModel>(specialityModelList);
        } else if (parent instanceof SpecialityModel) {
            Long specialityId = ((SpecialityModel) parent).getId();
            List<GroupModel> groupModelList = loadGroupList(course, specialityId);
            resultList = new ArrayList<BaseModel>(groupModelList);
        }
        return resultList;
    }

    @Override
    public List<StudentModel> loadStudentListBySpecialityIdAndCourse(Long specialityId, Integer course) {
        List<Group> groupList = groupDAO.findByCourseAndSpecialityId(course, specialityId);
        List<StudentModel> studentModelList = new ArrayList<StudentModel>();

        for (Group group : groupList) {
            List<Student> studentList = studentDAO.findByGroupId(group.getId());
            studentModelList.addAll(convertStudentsToStudentModels(studentList));
        }

        return studentModelList;
    }

    private List<StudentModel> convertStudentsToStudentModels(List<Student> studentList) {
        List<StudentModel> studentModelList = new ArrayList<StudentModel>(studentList.size());

        for (Student student : studentList) {
            StudentModel studentModel = new StudentModel();
            studentModel.setId(student.getId());
            studentModel.setName(new StringBuilder()
                    .append(student.getLastName())
                    .append(' ')
                    .append(student.getFirstName())
                    .append(' ')
                    .append(student.getMiddleName())
                    .toString());
            studentModel.setStudentIdNumber(student.getStudentIdNumber());

            String division = "";
            switch (student.getDivision()) {
                case INTRAMURAL:
                    division = "Дневное";
                    break;
                case EXTRAMURAL:
                    division = "Заочное";
                    break;
                case EVENINGSTUDY:
                    division = "Вечернее";
                    break;
            }
            studentModel.setDivision(division);

            String studyForm = "";
            switch (student.getStudyForm()) {
                case BUDGET:
                    studyForm = "Бюджетная";
                    break;
                case COMMERCIAL:
                    studyForm = "Коммерческая";
                    break;
            }
            studentModel.setStudyForm(studyForm);

            studentModelList.add(studentModel);
        }

        return studentModelList;
    }
}
