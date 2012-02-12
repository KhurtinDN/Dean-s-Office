package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sgu.csit.inoc.deansoffice.dao.FacultyDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;

import java.util.List;

import static junit.framework.Assert.assertTrue;

/**
 * User: MesheryakovAV
 * Date: 21.03.11
 * Time: 14:59
 */
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FacultyDAOITCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(FacultyDAOITCase.class);

    @Autowired
    private FacultyDAO facultyDAO;

    private List<Faculty> faculties;

    @Before
    public void init() {
        faculties = facultyDAO.findAll();
    }

    @Test
    public void findByShortName() {
        LOGGER.info("-- testFindByShortName --");
        LOGGER.info("[findAll] : total faculties - {}", faculties.size());

        for (int i = 0; i < faculties.size(); ++i) {
            Faculty faculty = faculties.get(i);

            LOGGER.info("{}. {}, Id = {}", new Object[]{i + 1, faculty.getShortName(), faculty.getId()});

            List<Faculty> newFaculties = facultyDAO.findByShortName(faculty.getShortName());

            LOGGER.info("Found {} faculties with name {}, first Id = {}",
                    new Object[]{newFaculties.size(), faculty.getShortName(), newFaculties.get(0).getId()});

            assertTrue("Поиск факультета по короткому имени " + faculty.getShortName() + ".",
                    faculty.getId().equals(newFaculties.get(0).getId()));
        }

        LOGGER.info("------------------------\n");
    }
}
