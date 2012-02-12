package ru.sgu.csit.inoc.deansoffice.dao.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.sgu.csit.inoc.deansoffice.dao.StipendDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Stipend;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertTrue;

/**
 * User: MesheryakovAV
 * Date: 22.03.11
 * Time: 13:12
 */
@ContextConfiguration(locations = {"classpath:ApplicationContext.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class StipendDAOITCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(StipendDAOITCase.class);

    @Autowired
    private StipendDAO stipendDAO;

    private List<Stipend> stipends;

    @Before
    public void init() {
        stipends = stipendDAO.findAll();
    }

    @Test
    public void findByAllParam() {
        LOGGER.info("-- testFindByAllParam --");
        Map<String, Stipend> differentStipends = new HashMap<String, Stipend>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Stipend stipend : stipends) {
            differentStipends.put(stipend.getType() + "_" + dateFormat.format(stipend.getStartDate()) + "_"
                    + dateFormat.format(stipend.getEndDate()) + "_" + stipend.getValue(), stipend);
        }
        LOGGER.info("[findAll] : total stipends - " + stipends.size());
        LOGGER.info("            total different stipends - " + differentStipends.size());

        int totalStipends = 0;
        LOGGER.info("[findByAllParam] : ");
        for (Map.Entry<String, Stipend> stipendEntry : differentStipends.entrySet()) {
            Stipend stipend = stipendEntry.getValue();
            List<Stipend> newStipends = stipendDAO.findByAllParam(stipend.getType(), stipend.getStartDate(),
                    stipend.getEndDate(), stipend.getValue());
            LOGGER.info("   found " + newStipends.size() + " stipends with param " + stipend.getType() + "_"
                    + dateFormat.format(stipend.getStartDate()) + "_" + dateFormat.format(stipend.getEndDate()) + "_"
                    + stipend.getValue());
            totalStipends += newStipends.size();
            boolean result = true;
            for (Stipend stpd : newStipends) {
                if (!stipend.getType().equals(stpd.getType()) || !stipend.getStartDate().equals(stpd.getStartDate())
                        || !stipend.getEndDate().equals(stpd.getEndDate())
                        || !stipend.getValue().equals(stpd.getValue())) {
                    result = false;
                }
            }
            assertTrue("Поиск стипендий по сигнатуре " + stipendEntry.getKey() + ".", result);
        }
        LOGGER.info("   total found stipends " + totalStipends);
        assertTrue("Общее количество найденных стипендий.", stipends.size() == totalStipends);
        LOGGER.info("------------------------\n");
    }
}


