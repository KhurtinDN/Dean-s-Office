package ru.sgu.csit.inoc.deansoffice.services.impl;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StipendDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.*;
import ru.sgu.csit.inoc.deansoffice.services.DirectiveService;
import ru.sgu.csit.inoc.deansoffice.services.StudentService;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 12:33
 */
public class DirectiveServiceImpl implements DirectiveService {
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");

    @Override
    public Directive createDirective(String type) {
        Directive directive;

        if (Directive.APPOINT_CAPTAINS.equalsIgnoreCase(type)) {
            directive = new Directive1();
        } else if (Directive.APPOINT_SOCIAL_STIPEND.equalsIgnoreCase(type)) {
            directive = new Directive2();
        } else {
            throw new RuntimeException("Can not create directive with type " + type);
        }

        return directive;
    }

    @Override
    public void executeDirective(Directive directive) {
        StudentDAO studentDAO = applicationContext.getBean(StudentDAO.class);
        GroupDAO groupDAO = applicationContext.getBean(GroupDAO.class);
        StipendDAO stipendDAO = applicationContext.getBean(StipendDAO.class);

        if (Directive.APPOINT_CAPTAINS.equals(directive.getType())) {
            Directive1.SourceData sourceData =
                    (Directive1.SourceData) directive.getData().getSourceData();

            Map<Group, Student> captains = sourceData.getCaptains();
            for (Map.Entry<Group, Student> captainEntry : captains.entrySet()) {
                Group group = groupDAO.findByName(captainEntry.getKey().getName()).get(0);
                List<Student> students = studentDAO.findByGroup(group);
                StudentService studentService = new StudentServiceImpl();

                for (Student student : students) {
                    if (studentService.equalsFullName(student, captainEntry.getValue())) {
                        student.setRole(Student.Role.CAPTAIN);
                        studentDAO.update(student);
                        break;
                    }
                }
            }
        } else if (Directive.APPOINT_SOCIAL_STIPEND.equals(directive.getType())) {
            Directive2.SourceData sourceData =
                    (Directive2.SourceData) directive.getData().getSourceData();

            Map<Student, Stipend> students = sourceData.getStudents();
            for (Map.Entry<Student, Stipend> studentEntry : students.entrySet()) {
                Student student = studentDAO.findById(studentEntry.getKey().getId());
                Stipend targetStipend = studentEntry.getValue();
                boolean stipendAlreadyExist = false;

                for (Stipend stipend : student.getStipends()) {
                    if (stipend.getType() == Stipend.StipendType.SOCIAL) {
                        stipendAlreadyExist = true; // todo action, if social stipend of student already exist
                    }
                }
                if (!stipendAlreadyExist) {
                    List<Stipend> stipends = stipendDAO.findByAllParam(targetStipend.getType(), targetStipend.getStartDate(),
                            targetStipend.getEndDate(), targetStipend.getValue());

                    if (!stipends.isEmpty()) {
                        targetStipend = stipends.get(0);
                    } else {
                        stipendDAO.save(targetStipend);
                    }
                    student.addStipend(targetStipend);
                    studentDAO.update(student);
                }
            }
        } else {
            throw new RuntimeException("Can not create directive with type " + directive.getType());
        }
    }
}
