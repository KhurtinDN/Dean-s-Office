package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sgu.csit.inoc.deansoffice.dao.AdditionalStudentDataDAO;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.dao.PassportDAO;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Passport;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

import java.util.List;

/**
 * Unit test for simple App.
 */
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class PassportDAOITCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(PassportDAOITCase.class);

    @Autowired
    private GroupDAO groupDAO;

    @Autowired
    private StudentDAO studentDAO;

    @Autowired
    private AdditionalStudentDataDAO additionalStudentDataDAO;

    @Autowired
    private PassportDAO passportDAO;

    @Test
    public void test() {
        Group group = groupDAO.findByName("111").get(0);
        List<Student> students = studentDAO.findByGroup(group);
        LOGGER.info(group.getName() + ": " + students.size());
        Student student = students.get(0);
        LOGGER.info("{}", student.getAdditionalData());
        LOGGER.info("{}", student.getAdditionalData().getPassports().size());

        Passport passport = new Passport(student);
        passportDAO.saveOrUpdate(passport);
        student.getAdditionalData().addPassport(passport);
        additionalStudentDataDAO.saveOrUpdate(student.getAdditionalData());
        studentDAO.saveOrUpdate(student);

        students = studentDAO.findByGroup(group);
//        for (Student stud : students) {
//            LOGGER.info(stud.getGroup().getName() + ": " + stud.getId() + ", "
//                    + stud.getAdditionalData().getPassports().size());
//        }
        LOGGER.info(group.getName() + " - " + students.size());
        for (Student stud : students) {
            LOGGER.info(stud.getGroup().getName() + ": " + stud.getId() + ", "
                    + stud.getAdditionalData().getPassports().size());
        }
//        student = studentDAO.findByGroupId(group.getId()).get(0);
//        LOGGER.info(student.getAdditionalData().getPassports().size());
    }
}
