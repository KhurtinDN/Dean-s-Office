package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sgu.csit.inoc.deansoffice.dao.SpecialityDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Faculty;
import ru.sgu.csit.inoc.deansoffice.domain.Speciality;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertTrue;

/**
 * User: MesheryakovAV
 * Date: 22.03.11
 * Time: 11:18
 */
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpecialityDAOITCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpecialityDAOITCase.class);

    @Autowired
    private SpecialityDAO specialityDAO;

    private List<Speciality> specialities;

    @Before
    public void init() {
        specialities = specialityDAO.findAll();
    }

    @Test
    public void findByShortName() {
        LOGGER.info("-- testFindByShortName -");
        Set<String> shortNames = new HashSet<String>();
        for (Speciality speciality : specialities) {
            shortNames.add(speciality.getShortName());
        }
        LOGGER.info("[findAll] : total specialities - " + specialities.size());
        LOGGER.info("            total shortNames - " + shortNames.size());

        int totalSpecialities = 0;
        LOGGER.info("[findByShortName] : ");
        for (String name : shortNames) {
            List<Speciality> newSpecialities = specialityDAO.findByShortName(name);
            LOGGER.info("   found " + newSpecialities.size() + " specialities with shortName " + name);
            totalSpecialities += newSpecialities.size();
            boolean result = true;
            for (Speciality speciality : newSpecialities) {
                if (!name.equals(speciality.getShortName())) {
                    result = false;
                }
            }
            assertTrue("Поиск специальностей по короткому имени " + name + ".", result);
        }
        LOGGER.info("   total found specialities " + totalSpecialities);
        assertTrue("Общее количество найденных специальностей.", specialities.size() == totalSpecialities);
        LOGGER.info("------------------------\n");
    }

    @Test
    public void findByFaculty() {
        LOGGER.info("-- testFindByFaculty ---");
        Set<Faculty> faculties = new HashSet<Faculty>();
        for (Speciality speciality : specialities) {
            faculties.add(speciality.getFaculty());
        }
        LOGGER.info("[findAll] : total specialities - " + specialities.size());
        LOGGER.info("            total faculties - " + faculties.size());

        int totalSpecialities = 0;
        LOGGER.info("[findByShortFaculty] : ");
        for (Faculty faculty : faculties) {
            List<Speciality> newSpecialities = specialityDAO.findByFaculty(faculty);
            LOGGER.info("   found " + newSpecialities.size() + " specialities with faculty "
                    + faculty.getShortName());
            totalSpecialities += newSpecialities.size();
            boolean result = true;
            for (Speciality speciality : newSpecialities) {
                if (!faculty.getId().equals(speciality.getFaculty().getId())) {
                    result = false;
                }
            }
            assertTrue("Поиск специальностей по факультету " + faculty.getShortName() + ".", result);
        }
        LOGGER.info("   total found specialities " + totalSpecialities);
        assertTrue("Общее количество найденных специальностей.", specialities.size() == totalSpecialities);
        LOGGER.info("------------------------\n");
    }

    @Test
    public void findByFacultyId() {
        LOGGER.info("-- testFindByFacultyId ---");
        Set<Long> facultiesId = new HashSet<Long>();
        for (Speciality speciality : specialities) {
            facultiesId.add(speciality.getFaculty().getId());
        }
        LOGGER.info("[findAll] : total specialities - " + specialities.size());
        LOGGER.info("            total faculties - " + facultiesId.size());

        int totalSpecialities = 0;
        LOGGER.info("[findByShortFacultyId] : ");
        for (Long facultyId : facultiesId) {
            List<Speciality> newSpecialities = specialityDAO.findByFacultyId(facultyId);
            LOGGER.info("   found " + newSpecialities.size() + " specialities with faculty Id "
                    + facultyId);
            totalSpecialities += newSpecialities.size();
            boolean result = true;
            for (Speciality speciality : newSpecialities) {
                if (!facultyId.equals(speciality.getFaculty().getId())) {
                    result = false;
                }
            }
            assertTrue("Поиск специальностей по Id факультета " + facultyId + ".", result);
        }
        LOGGER.info("   total found specialities " + totalSpecialities);
        assertTrue("Общее количество найденных специальностей.", specialities.size() == totalSpecialities);
        LOGGER.info("------------------------\n");
    }
}


