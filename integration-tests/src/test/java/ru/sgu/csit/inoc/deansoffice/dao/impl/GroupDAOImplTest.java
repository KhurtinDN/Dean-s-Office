package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sgu.csit.inoc.deansoffice.dao.GroupDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Group;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertTrue;

/**
 * User: MesheryakovAV
 * Date: 22.03.11
 * Time: 10:41
 */
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class GroupDAOImplTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(GroupDAOImplTest.class);

    @Autowired
    private GroupDAO groupDAO;

    private List<Group> groups;

    @Before
    public void init() {
        groups = groupDAO.findAll();
    }

    @Test
    public void findByCourseAndSpeciality() {
        LOGGER.info("-- testFindByCourseAndSpeciality");
        Set<Integer> courses = new HashSet<Integer>();
        Set<Speciality> specialities = new HashSet<Speciality>();
        for (Group group : groups) {
            courses.add(group.getCourse());
            specialities.add(group.getSpeciality());
        }
        LOGGER.info("[findAll] : total groups - " + groups.size());
        LOGGER.info("            total courses - " + courses.size());
        LOGGER.info("            total specialities - " + specialities.size());

        int totalGroups = 0;
        LOGGER.info("[findByCourseAndSpeciality] : ");
        for (Integer course : courses) {
            for (Speciality speciality : specialities) {
                List<Group> newGroups = groupDAO.findByCourseAndSpeciality(course, speciality);
                LOGGER.info("   found " + newGroups.size() + " groups with course " + course +
                        " and speciality " + speciality.getShortName());
                totalGroups += newGroups.size();
                boolean result = true;
                for (Group group : newGroups) {
                    if (!course.equals(group.getCourse()) || !speciality.getId().equals(group.getSpeciality().getId())) {
                        result = false;
                    }
                }
                assertTrue("Поиск групп по курсу " + course + " и специальности " + speciality.getShortName() + ".",
                        result);
            }
        }
        LOGGER.info("   total found groups " + totalGroups);
        assertTrue("Общее количество найденных групп.", groups.size() == totalGroups);
        LOGGER.info("------------------------\n");
    }

    @Test
    public void findByCourseAndSpecialityId() {
        LOGGER.info("-- testFindByCourseAndSpecialityId");
        Set<Integer> courses = new HashSet<Integer>();
        Set<Long> specialitiesId = new HashSet<Long>();
        for (Group group : groups) {
            courses.add(group.getCourse());
            specialitiesId.add(group.getSpeciality().getId());
        }
        LOGGER.info("[findAll] : total groups - " + groups.size());
        LOGGER.info("            total courses - " + courses.size());
        LOGGER.info("            total specialitiesId - " + specialitiesId.size());

        int totalGroups = 0;
        LOGGER.info("[findByCourseAndSpecialityId] : ");
        for (Integer course : courses) {
            for (Long specialityId : specialitiesId) {
                List<Group> newGroups = groupDAO.findByCourseAndSpecialityId(course, specialityId);
                LOGGER.info("   found " + newGroups.size() + " groups with course " + course +
                        " and specialityId " + specialityId);
                totalGroups += newGroups.size();
                boolean result = true;
                for (Group group : newGroups) {
                    if (!course.equals(group.getCourse()) || !group.getSpeciality().getId().equals(specialityId)) {
                        result = false;
                    }
                }
                assertTrue("Поиск групп по курсу " + course + " и Id специальности " + specialityId + ".", result);
            }
        }
        LOGGER.info("   total found groups " + totalGroups);
        assertTrue("Общее количество найденных групп.", groups.size() == totalGroups);
        LOGGER.info("------------------------\n");
    }

    @Test
    public void findByName() {
        LOGGER.info("-- testFindByName ------");
        Set<String> names = new HashSet<String>();
        for (Group group : groups) {
            names.add(group.getName());
        }
        LOGGER.info("[findAll] : total groups - " + groups.size());
        LOGGER.info("            total names - " + names.size());

        int totalGroups = 0;
        LOGGER.info("[findByName] : ");
        for (String name : names) {
            List<Group> newGroups = groupDAO.findByName(name);
            LOGGER.info("   found " + newGroups.size() + " groups with name " + name);
            totalGroups += newGroups.size();
            boolean result = true;
            for (Group group : newGroups) {
                if (!name.equals(group.getName())) {
                    result = false;
                }
            }
            assertTrue("Поиск групп по имени " + name + ".", result);
        }
        LOGGER.info("   total found groups " + totalGroups);
        assertTrue("Общее количество найденных групп.", groups.size() == totalGroups);
        LOGGER.info("------------------------\n");
    }
}

