package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sgu.csit.inoc.deansoffice.dao.StudentDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;
import ru.sgu.csit.inoc.deansoffice.domain.Student;

import java.util.*;

import static junit.framework.Assert.assertTrue;

/**
 * User: MesheryakovAV
 * Date: 09.03.11
 * Time: 10:05
 */
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentDAOImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentDAOImplTest.class);

    @Autowired
    private StudentDAO studentDAO;

    private List<Student> students;

    @Before
    public void init() {
        students = studentDAO.findAll();
    }

    @Test
    public void findByGroup() {
        LOGGER.info("-- testFindByGroup -----");
        Map<Group, Integer> groupsMap = new HashMap<Group, Integer>();
        for (Student student : students) {
            Group group = student.getGroup();
            Integer value = 1;

            if (groupsMap.containsKey(group)) {
                value = groupsMap.get(group) + 1;
            }
            groupsMap.put(group, value);
        }
        for (Map.Entry<Group, Integer> entry : groupsMap.entrySet()) {
            Group group = entry.getKey();
            Integer value = entry.getValue();

            LOGGER.info("[findAll] " + group.getName() + " : " + value);
            Integer newValue = studentDAO.findByGroup(group).size();
            LOGGER.info("[findByGroup] " + group.getName() + " : " + newValue);
            assertTrue("Количество студентов в " + group.getName() + " группе.", value.equals(newValue));
        }
        LOGGER.info("------------------------\n");
    }

    @Test
    public void findByGroupId() {
        LOGGER.info("-- testFindByGroupId ---");
        Map<Group, Integer> groupsMap = new HashMap<Group, Integer>();
        for (Student student : students) {
            Group group = student.getGroup();
            Integer value = 1;

            if (groupsMap.containsKey(group)) {
                value = groupsMap.get(group) + 1;
            }
            groupsMap.put(group, value);
        }
        for (Map.Entry<Group, Integer> entry : groupsMap.entrySet()) {
            Group group = entry.getKey();
            Integer value = entry.getValue();

            LOGGER.info("[findAll] " + group.getName() + " : " + value);
            Integer newValue = studentDAO.findByGroupId(group.getId()).size();
            LOGGER.info("[findByGroupId] " + group.getName() + " : " + newValue);
            assertTrue("Количество студентов в " + group.getName() + " группе.", value.equals(newValue));
        }
        LOGGER.info("------------------------\n");
    }

    @Test
    public void findBySpecialityIdAndCourse() {
        LOGGER.info("-- testFindBySpecialityIdAndCourse --");
        Set<Speciality> specialities = new HashSet<Speciality>();
        Set<Integer> courses = new HashSet<Integer>();
        for (Student student : students) {
            specialities.add(student.getSpeciality());
            courses.add(student.getGroup().getCourse());
        }
        for (Integer course : courses) {
            for (Speciality speciality : specialities) {
                Integer count = 0;
                for (Student student : students) {
                    if (student.getSpeciality().getId().equals(speciality.getId())
                            && student.getGroup().getCourse().equals(course)) {
                        count++;
                    }
                }
                LOGGER.info("[findAll] " + speciality.getId() + ", " + course + " : "
                        + count);
                Integer newCount = studentDAO.findBySpecialityIdAndCourse(speciality.getId(), course).size();
                LOGGER.info("[findBySpecialityIdAndCourse] " + speciality.getId() + ", " + course + " : "
                        + newCount);
                assertTrue("Количество студентов " + course + " курса, " + speciality.getId() + " специальности.",
                        count.equals(newCount));
            }
        }
        LOGGER.info("------------------------\n");
    }
}
