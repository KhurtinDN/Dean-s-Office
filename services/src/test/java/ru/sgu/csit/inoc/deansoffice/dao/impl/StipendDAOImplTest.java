package ru.sgu.csit.inoc.deansoffice.dao.impl;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.sgu.csit.inoc.deansoffice.dao.StipendDAO;
import ru.sgu.csit.inoc.deansoffice.domain.Stipend;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 22.03.11
 * Time: 13:12
 */

/**
 * Unit test for StipendDAOImpl.
 */
public class StipendDAOImplTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public StipendDAOImplTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(ru.sgu.csit.inoc.deansoffice.dao.impl.StipendDAOImplTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    private static ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
    private static StipendDAO stipendDAO = applicationContext.getBean(StipendDAOImpl.class);
    private static List<Stipend> stipends;

    static {
        stipends = stipendDAO.findAll();
    }

    public void testFindByAllParam() {
        System.out.println("-- testFindByAllParam --");
        Map<String, Stipend> differentStipends = new HashMap<String, Stipend>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        for (Stipend stipend: stipends) {
            differentStipends.put(stipend.getType() + "_" + dateFormat.format(stipend.getStartDate()) + "_"
                    + dateFormat.format(stipend.getEndDate()) + "_" + stipend.getValue(), stipend);
        }
        System.out.println("[findAll] : total stipends - " + stipends.size());
        System.out.println("            total different stipends - " + differentStipends.size());

        int totalStipends = 0;
        System.out.println("[findByAllParam] : ");
        for (Map.Entry<String, Stipend> stipendEntry : differentStipends.entrySet()) {
            Stipend stipend = stipendEntry.getValue();
            List<Stipend> newStipends = stipendDAO.findByAllParam(stipend.getType(), stipend.getStartDate(),
                    stipend.getEndDate(), stipend.getValue());
            System.out.println("   found " + newStipends.size() + " stipends with param " + stipend.getType() + "_"
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
        System.out.println("   total found stipends " + totalStipends);
        assertTrue("Общее количество найденных стипендий.", stipends.size() == totalStipends);
        System.out.println("------------------------\n");
    }
}


