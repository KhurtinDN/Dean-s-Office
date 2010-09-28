package ru.sgu.csit.inoc.deansoffice;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.*;
import ru.sgu.csit.inoc.deansoffice.domain.*;

import java.util.List;

/**
 * .
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 10, 2010
 * Time: 11:14:30 AM
 */
public class TestMain {
    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");

    private StudentDAO studentDAO = applicationContext.getBean(StudentDAO.class);
    private GroupDAO groupDAO = applicationContext.getBean(GroupDAO.class);
    private SpecialityDAO specialityDAO = applicationContext.getBean(SpecialityDAO.class);
    private FacultyDAO facultyDAO = applicationContext.getBean(FacultyDAO.class);
    private DeanDAO deanDAO = applicationContext.getBean(DeanDAO.class);

    public static void main(String[] args) {
        TestMain testMain = new TestMain();
        testMain.createFaculty();
        testMain.createSpecialities();
        testMain.createGroups();
        testMain.createStudents();
    }

    private void createFaculty() {
        Dean dean = new Dean();
        dean.setFirstName("Антонина");
        dean.setMiddleName("Гавриловна");
        dean.setLastName("Федорова");
        deanDAO.save(dean);

        Faculty faculty = new Faculty();
        faculty.setFullName("Компьютерные науки и информационные технологии");
        faculty.setShortName("КНиИТ");
        faculty.setDean(dean);
        faculty.setCourseCount(6);
        facultyDAO.save(faculty);
    }

    private void createSpecialities() {
        List<Faculty> facultyList = facultyDAO.findAll(Faculty.class);

        for (Faculty faculty : facultyList) {

            Speciality speciality = new Speciality();
            speciality.setName("Прикладная математика и информатика");
            speciality.setShortName("ПМИ");
            speciality.setCode("1");
            speciality.setFaculty(faculty);
            specialityDAO.save(speciality);

            speciality = new Speciality();
            speciality.setName("Вычислительные машины, комплексы, системы, сети");
            speciality.setShortName("ВМ");
            speciality.setCode("2");
            speciality.setFaculty(faculty);
            specialityDAO.save(speciality);

            speciality = new Speciality();
            speciality.setName("Компьютерная безопасность");
            speciality.setShortName("КБ");
            speciality.setCode("3");
            speciality.setFaculty(faculty);
            specialityDAO.save(speciality);
        }
    }

    private void createGroups() {
        List<Speciality> specialityList = specialityDAO.findAll(Speciality.class);
        for (Speciality speciality : specialityList) {
            int courseCount = speciality.getFaculty().getCourseCount();
            for (int groupCount = 1; groupCount <= 2; ++groupCount) {
                for (int course = 1; course <= courseCount; ++course) {
                    Group group = new Group();
                    group.setName(course + speciality.getCode() + groupCount);
                    group.setCourse(course);
                    group.setSpeciality(speciality);
                    groupDAO.save(group);
                }
            }
        }
    }

    private void createStudents() {
        List<Group> groupList = groupDAO.findAll(Group.class);

        for (Group group : groupList) {
            for (int studentCount = 1; studentCount <= 20; ++studentCount) {
                Student student = new Student();
                student.setFirstName("Denis" + studentCount);
                student.setLastName("Khurtin" + studentCount);
                student.setMiddleName("Nikolaevich" + studentCount);
                student.setCource(group.getCourse());
                student.setStudyForm(Student.StudyForm.BUDGET);
                student.setGroup(group);
                student.setStudentIdNumber("" + ((int) (Math.random() * 100000)));
                student.setDivision(Student.Division.INTRAMURAL);

                studentDAO.save(student);
            }
        }
    }
}
