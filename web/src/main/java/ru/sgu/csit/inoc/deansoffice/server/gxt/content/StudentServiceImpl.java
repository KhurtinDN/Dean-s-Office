package ru.sgu.csit.inoc.deansoffice.server.gxt.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sgu.csit.inoc.deansoffice.client.gxt.content.StudentService;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Student;
import ru.sgu.csit.inoc.deansoffice.shared.dto.StudentDto;

import java.util.ArrayList;
import java.util.List;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Oct 2, 2010
 * Time: 4:57:14 PM
 */
@Service("studentService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDAO studentDAO;

    @Override
    public List<StudentDto> downloadStudents(Long groupId) {
        List<StudentDto> result = new ArrayList<StudentDto>();

        List<Student> studentList = studentDAO.findByGroupId(groupId);
        for (Student student : studentList) {
            if (student.getGroup().getId().equals(groupId)) {
                StudentDto studentDto = new StudentDto();
                studentDto.setId(student.getId());
                studentDto.setName(student.getFirstName() + " " + student.getMiddleName() + " " + student.getLastName());
                studentDto.setStudentIdNumber(student.getStudentIdNumber());

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
                studentDto.setDivision(division);

                String studyForm = "";
                switch (student.getStudyForm()) {
                    case BUDGET:
                        studyForm = "Бюджетная";
                        break;
                    case COMMERCIAL:
                        studyForm = "Коммерческая";
                        break;
                }
                studentDto.setStudyForm(studyForm);

                result.add(studentDto);
            }
        }

        return result;
    }
}